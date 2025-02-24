package com.infinity.nto.dto.mapper;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.entity.EmployeeData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeDataMapper {
    public static EmployeeDataDto toEmployeeDataDto(EmployeeData employeeData) {
        return EmployeeDataDto.builder()
                .name(employeeData.getName())
                .photo(employeeData.getPhoto())
                .employeePosition(employeeData.getEmployeePosition())
                .lastVisit(employeeData.getLastVisit())
                .build();
    }
}
