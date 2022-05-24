package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.DepartmentToResponseConverter;
import org.keelfy.eljur.api.model.response.DepartmentResponse;
import org.keelfy.eljur.data.entity.Department;
import org.keelfy.eljur.data.repository.DepartmentRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentToResponseConverter departmentToResponseConverter;

    @PreAuthorize("hasAnyAuthority(T(org.keelfy.eljur.api.util.Authority).ADMIN, " +
            "T(org.keelfy.eljur.api.util.Authority).SYSTEM)")
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentToResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public Department getDepartmentById(@NonNull Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department by id=" + id + " not found"));
    }

}
