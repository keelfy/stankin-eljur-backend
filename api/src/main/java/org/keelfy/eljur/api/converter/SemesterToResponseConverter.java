package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.SemesterResponse;
import org.keelfy.eljur.data.entity.Semester;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class SemesterToResponseConverter implements Converter<Semester, SemesterResponse> {

    private final SemesterSubjectToResponseConverter subjectResponseConverter;

    @NonNull
    @Override
    public SemesterResponse convert(@NonNull Semester source) {
        return new SemesterResponse()
                .setId(source.getId())
                .setOrdinal(source.getOrdinal())
                .setFirstModuleDeadline(source.getFirstModuleDeadline())
                .setSecondModuleDeadline(source.getSecondModuleDeadline())
                .setSessionDeadline(source.getSessionDeadline())
                .setSemesterSubjectList(source.getSemesterSubjectList().stream()
                        .sorted((o1, o2) -> {
                            if (o2.getCoefficient() != null) {
                                return o2.getCoefficient().compareTo(o1.getCoefficient());
                            } else {
                                return 1;
                            }
                        })
                        .map(subjectResponseConverter::convert)
                        .collect(Collectors.toList()));
    }

}
