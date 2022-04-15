package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.GroupSemesterToResponseConverter;
import org.keelfy.eljur.api.model.response.GroupSemesterResponse;
import org.keelfy.eljur.data.entity.GroupSemester;
import org.keelfy.eljur.data.repository.GroupSemesterRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class GroupSemesterService {

    private final CredentialsService credentialsService;

    private final GroupSemesterRepository groupSemesterRepository;

    private final GroupSemesterToResponseConverter groupSemesterToResponseConverter;

    @NonNull
    public GroupSemesterResponse getGroupSemester() {
        final var semester = getCurrentGroupSemester();
        return groupSemesterToResponseConverter.convert(semester);
    }

    @NonNull
    public GroupSemester getCurrentGroupSemester() {
        final var student = credentialsService.getCredentials();
        final var group = student.getGroup();

        if (group == null) {
            throw new IllegalArgumentException("No group");
        }

        final var now = ZonedDateTime.now();
        return groupSemesterRepository.findByGroup(group)
                .stream()
                .sorted(Comparator.comparing(GroupSemester::getOrdinal))
                .filter(o -> o.getSessionDeadline().isAfter(now))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Current group semester not found"));
    }

    @NonNull
    public GroupSemester getGroupSemesterById(@NonNull Long id) {
        return groupSemesterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group semester by id=" + id + " not found"));
    }

}
