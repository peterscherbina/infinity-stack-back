package com.infinity.nto.service;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.repository.EmployeeDataRepository;
import com.infinity.nto.repository.EmployeeRepository;
import com.infinity.nto.repository.EntryRepository;
import com.infinity.nto.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test AdminService")
public class AdminServiceTest {
    private static final String EMPLOYEE_LOGIN = "employee";
    private static final String SELF_LOGIN = "admin";


    private AdminService adminService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeDataRepository employeeDataRepository;
    @Mock
    private EntryRepository entryRepository;

    @BeforeEach
    void init() {
        adminService = new AdminServiceImpl(employeeRepository, employeeDataRepository, entryRepository);
    }

    @DisplayName("Test AdminService.getEmployeeInfo")
    @Test
    void testGetEmployeeInfo() {
        when(employeeRepository.existsByLogin(EMPLOYEE_LOGIN)).thenReturn(true);
        when(employeeRepository.existsByLogin(SELF_LOGIN)).thenReturn(true);

        when(employeeDataRepository.existsByEmployeeLogin(EMPLOYEE_LOGIN)).thenReturn(true);
        when(employeeDataRepository.existsByEmployeeLogin(SELF_LOGIN)).thenReturn(true);

        EmployeeDataDto employeeDataDto = new EmployeeDataDto(
                "nane",
                "photo",
                "ep",
                LocalDateTime.now()
        );

        when(employeeDataRepository.getEmployeeDataDtoByLogin(EMPLOYEE_LOGIN))
                .thenReturn(employeeDataDto);

        assertThat(adminService.getEmployeeInfo(EMPLOYEE_LOGIN, SELF_LOGIN))
                .isEqualTo(employeeDataDto);
    }

    @DisplayName("Test AdminService.setBlockCondition & AdminService.isEmployeeBlocked")
    @Test
    void testSetBlockConditionAndIsEmployeeBlocked() {
        when(employeeRepository.existsByLogin(EMPLOYEE_LOGIN)).thenReturn(true);
        when(employeeRepository.existsByLogin(SELF_LOGIN)).thenReturn(true);

        when(employeeRepository.getIsBlockByLogin(EMPLOYEE_LOGIN)).thenReturn(false);
        when(employeeRepository.getIsBlockByLogin(SELF_LOGIN)).thenReturn(false);

        assertThat(adminService.isEmployeeBlocked(EMPLOYEE_LOGIN, SELF_LOGIN)).isFalse();

        when(employeeRepository.getIsBlockByLogin(EMPLOYEE_LOGIN)).thenReturn(true);
        when(employeeRepository.getIsBlockByLogin(SELF_LOGIN)).thenReturn(true);

        assertThat(adminService.isEmployeeBlocked(EMPLOYEE_LOGIN, SELF_LOGIN)).isTrue();
    }

}
