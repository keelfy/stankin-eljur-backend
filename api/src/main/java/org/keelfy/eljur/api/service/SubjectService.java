package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.SubjectToResponseConverter;
import org.keelfy.eljur.api.exception.DuplicatedEntityException;
import org.keelfy.eljur.api.model.request.CreateSubjectRequest;
import org.keelfy.eljur.api.model.request.EditSubjectRequest;
import org.keelfy.eljur.api.model.response.SubjectResponse;
import org.keelfy.eljur.data.entity.Subject;
import org.keelfy.eljur.data.repository.SubjectRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectToResponseConverter subjectToResponseConverter;

    @NonNull
    public List<SubjectResponse> listSubjects() {
        return subjectRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Subject::getName))
                .map(subjectToResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public SubjectResponse createSubject(@NotNull CreateSubjectRequest request) {
        if (subjectRepository.findByName(request.getName()).isPresent()) {
            throw new DuplicatedEntityException("Subject by name=" + request.getName() + " already exists");
        }

        final var subject = new Subject()
                .setName(request.getName());
        return subjectToResponseConverter.convert(subjectRepository.saveAndFlush(subject));
    }

    @NonNull
    public SubjectResponse editSubject(@NonNull Long subjectId, @NonNull EditSubjectRequest request) {
        final var subject = getSubjectById(subjectId);
        return subjectToResponseConverter.convert(subjectRepository.saveAndFlush(subject
                .setName(request.getName())
        ));
    }

    @NonNull
    public Subject getSubjectById(@NonNull Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subject by id=" + id + " not found"));
    }

}
