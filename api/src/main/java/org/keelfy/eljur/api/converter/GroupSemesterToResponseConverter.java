package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.GroupSemesterResponse;
import org.keelfy.eljur.api.model.response.SemesterSubjectResponse;
import org.keelfy.eljur.data.entity.GroupSemester;
import org.keelfy.eljur.data.entity.Subject;
import org.keelfy.eljur.data.entity.embeded.SubjectPart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class GroupSemesterToResponseConverter implements Converter<GroupSemester, GroupSemesterResponse> {

    private final SubjectPartEntryToSemesterSubjectResponseConverter subjectResponseConverter;

    @NonNull
    @Override
    public GroupSemesterResponse convert(@NonNull GroupSemester source) {
        return new GroupSemesterResponse()
                .setId(source.getId())
                .setOrdinal(source.getOrdinal())
                .setFirstModuleDeadline(source.getFirstModuleDeadline())
                .setSecondModuleDeadline(source.getSecondModuleDeadline())
                .setSessionDeadline(source.getSessionDeadline())
                .setSubjectList(convertSubjects(source.getSubjects()));
    }

    private List<SemesterSubjectResponse> convertSubjects(Map<Subject, SubjectPart> source) {
        return source
                .entrySet()
                .stream()
                .map(subjectResponseConverter::convert)
                .collect(Collectors.toList());
    }

}
