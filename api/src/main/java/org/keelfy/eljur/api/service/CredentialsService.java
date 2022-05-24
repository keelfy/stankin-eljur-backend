package org.keelfy.eljur.api.service;

import com.google.i18n.phonenumbers.NumberParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.converter.CredentialsToDtoConverter;
import org.keelfy.eljur.api.exception.CredentialsNotFoundException;
import org.keelfy.eljur.api.exception.DuplicatedEntityException;
import org.keelfy.eljur.api.model.CredentialsDto;
import org.keelfy.eljur.api.model.request.ChangeCredentialsRequest;
import org.keelfy.eljur.api.model.request.CreateCredentialsRequest;
import org.keelfy.eljur.api.util.PhoneNumberUtils;
import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.model.UserCredentials;
import org.keelfy.eljur.data.repository.CredentialsRepository;
import org.keelfy.eljur.data.repository.DepartmentRepository;
import org.keelfy.eljur.data.repository.GroupRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CredentialsService implements UserDetailsService {

    private static final String STUDENT_AUTHORITY = "student";

    private static final String TEACHER_AUTHORITY = "teacher";

    private final CredentialsRepository credentialsRepository;

    private final CredentialsToDtoConverter credentialsToDtoConverter;

    private final DepartmentRepository departmentRepository;

    private final GroupRepository groupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getCredentialsByUsername(username);
    }

    @NonNull
    public Credentials createCredentials(@NonNull CredentialsDto credentialsDto) {
        return credentialsRepository.saveAndFlush(new Credentials()
                .setUsername(credentialsDto.getUsername())
                .setEmail(credentialsDto.getEmail())
                .setCreatedAt(ZonedDateTime.now())
                .setEnabled(false));
    }

    public void enableCredentials(@NonNull Credentials credentials) {
        credentials.setEnabled(true);
    }

    public void updateLatestAuthTime(Credentials credentials, ZonedDateTime time, boolean successful) {
        credentialsRepository.findById(credentials.getId())
                .map(c -> c.setLatestSuccessfulAuthentication(time))
                .ifPresent(credentialsRepository::saveAndFlush);
    }

    public Credentials getCredentials() {
        final var principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getCredentialsByUsername(principal);
    }

    public CredentialsDto getCredentialsDto() {
        final var credentials = getCredentials();
        return credentialsToDtoConverter.convert(credentials);
    }

    public void changeCredentials(Credentials credentials, String email, String phoneNumber) throws NumberParseException {
        Optional.ofNullable(email).ifPresent(credentials::setEmail);

        if (phoneNumber != null) {
            final var formattedPhoneNumber = PhoneNumberUtils.formatPhoneNumber(phoneNumber);
            Optional.ofNullable(formattedPhoneNumber).ifPresent(credentials::setPhoneNumber);
        }
    }

    public void changeCredentials(ChangeCredentialsRequest request) throws NumberParseException {
        final var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var credentials = getCredentialsByUsername(username);
        changeCredentials(credentials, request.getEmail(), request.getPhoneNumber());
    }

    public void createCredentials(@NonNull CreateCredentialsRequest request) {
        if (credentialsRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new DuplicatedEntityException("Credentials by username=" + request.getUsername() + " already exists!");
        }

        final var department = Optional.ofNullable(request.getDepartmentId())
                .flatMap(departmentRepository::findById)
                .orElse(null);
        final var group = Optional.ofNullable(request.getGroupId())
                .flatMap(groupRepository::findById)
                .orElse(null);
        final var credentials = new Credentials()
                .setEmail(request.getEmail())
                .setDepartment(department)
                .setGroup(group)
                .setFirstName(request.getFirstName())
                .setSecondName(request.getSecondName())
                .setMiddleName(request.getMiddleName())
                .setEnabled(false)
                .setLocked(false)
                .setPhoneNumber(request.getPhoneNumber())
                .setRecordBookNumber(request.getRecordBookNumber())
                .setStudentTicketNumber(request.getStudentTicketNumber())
                .setUsername(request.getUsername())
                .setRoles(request.getRole());
        credentialsRepository.saveAndFlush(credentials);
    }

    @NonNull
    public Credentials getCredentialsById(@NonNull Long id) {
        return credentialsRepository.findById(id)
                .orElseThrow(() -> new CredentialsNotFoundException("Credentials by id=" + id + " not found"));
    }

    @NonNull
    public Credentials getCredentialsByEmail(@NonNull String email) {
        return credentialsRepository.findByEmail(email)
                .orElseThrow(() -> new CredentialsNotFoundException("Credentials by email=" + email + " not found"));
    }

    @NonNull
    public Credentials getCredentialsByUsername(@NonNull String username) {
        return credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new CredentialsNotFoundException("Credentials by username=" + username + " not found"));
    }

    public boolean isStudent(@NonNull UserCredentials credentials) {
        return credentials.getLiteralAuthorities().contains(STUDENT_AUTHORITY);
    }

    public boolean isTeacher(@NonNull UserCredentials credentials) {
        return credentials.getLiteralAuthorities().contains(TEACHER_AUTHORITY);
    }

}
