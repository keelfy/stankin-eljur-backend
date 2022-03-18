package org.keelfy.eljur.service.impl;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.data.Credentials;
import org.keelfy.eljur.data.repository.CredentialsRepository;
import org.keelfy.eljur.exception.CredentialsNotFoundException;
import org.keelfy.eljur.model.UserCredentials;
import org.keelfy.eljur.service.CredentialsService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
@Service
@RequiredArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    private static final String STUDENT_AUTHORITY = "student";

    private static final String TEACHER_AUTHORITY = "teacher";

    private final CredentialsRepository credentialsRepository;

    @NonNull
    @Override
    public Credentials getById(@NonNull BigInteger id) {
        return credentialsRepository.findById(id)
                .orElseThrow(() -> new CredentialsNotFoundException("Credentials by id=" + id + " not found"));
    }

    @Override
    public boolean isStudent(@NonNull UserCredentials credentials) {
        return credentials.getLiteralAuthorities().contains(STUDENT_AUTHORITY);
    }

    @Override
    public boolean isTeacher(@NonNull UserCredentials credentials) {
        return credentials.getLiteralAuthorities().contains(TEACHER_AUTHORITY);
    }

}
