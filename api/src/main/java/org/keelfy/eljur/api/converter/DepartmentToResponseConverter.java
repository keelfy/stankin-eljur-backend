package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.DepartmentResponse;
import org.keelfy.eljur.data.entity.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class DepartmentToResponseConverter implements Converter<Department, DepartmentResponse> {

    @NonNull
    @Override
    public DepartmentResponse convert(@NonNull Department source) {
        return new DepartmentResponse()
                .setId(source.getId())
                .setName(source.getName())
                .setShortName(source.getShortName());
    }

}
