package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.GroupResponse;
import org.keelfy.eljur.data.entity.Group;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class GroupToResponseConverter implements Converter<Group, GroupResponse> {

    private final DepartmentToResponseConverter departmentToResponseConverter;

    private final CredentialsToStudentResponseConverter credentialsToStudentResponseConverter;

    @NonNull
    @Override
    public GroupResponse convert(@NonNull Group source) {
        return new GroupResponse()
                .setId(source.getId())
                .setName(source.getName())
                .setActive(source.getActive())
                .setDegree(source.getDegree())
                .setStudyStartYear(source.getStudyStartYear())
                .setDepartment(Optional.ofNullable(source.getDepartment())
                        .map(departmentToResponseConverter::convert)
                        .orElse(null))
                .setHeadman(Optional.ofNullable(source.getHeadman())
                        .map(credentialsToStudentResponseConverter::convert)
                        .orElse(null));
    }

}
