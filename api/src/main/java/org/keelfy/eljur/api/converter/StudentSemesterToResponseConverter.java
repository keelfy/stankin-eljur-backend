package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.api.model.response.StudentSemesterResponse;
import org.keelfy.eljur.data.entity.Grade;
import org.keelfy.eljur.data.entity.StudentSemester;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class StudentSemesterToResponseConverter implements Converter<StudentSemester, StudentSemesterResponse> {

    private final GradeToResponseConverter gradeToResponseConverter;

    @NonNull
    @Override
    public StudentSemesterResponse convert(@NonNull StudentSemester source) {
        return new StudentSemesterResponse()
                .setId(source.getId())
                .setGrades(convertGradeList(source.getGrades()));
    }

    private List<GradeResponse> convertGradeList(List<Grade> gradeList) {
        return gradeList.stream().map(gradeToResponseConverter::convert).collect(Collectors.toList());
    }

}
