package com.infinity.nto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDataDto {
    private String name;
    private String photo;
    private String employeePosition;
    private LocalDateTime lastVisit;
}
