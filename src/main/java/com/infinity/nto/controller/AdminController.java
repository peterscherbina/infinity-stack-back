package com.infinity.nto.controller;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.dto.EntryDto;
import com.infinity.nto.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/am-i-admin")
    private void amIAdmin() {}

    @PostMapping("/panel/get-employee-info")
    private EmployeeDataDto getEmployeeInfo(@RequestParam("employee-login") String employeeLogin, Authentication authentication) {
        String login = authentication.getName();
        return adminService.getEmployeeInfo(employeeLogin, login);
    }

    @PostMapping("/panel/set-block-condition")
    private void setBlockCondition(@RequestParam("employee-login") String employeeLogin,
                                   @RequestParam("block-condition") boolean blockCondition,
                                   Authentication authentication) {
        String login = authentication.getName();
        adminService.setBlockCondition(employeeLogin, blockCondition, login);
    }

    @PostMapping("/panel/get-employee-entry-list")
    private List<EntryDto> getEmployeeEntryList(@RequestParam("employee-login") String employeeLogin,
                                                Authentication authentication) {
        String login = authentication.getName();
        return adminService.getEmployeeEntryList(employeeLogin, login);
    }

    @PostMapping("/panel/is-employee-blocked")
    private boolean isEmployeeBlocked(@RequestParam("employee-login") String employeeLogin,
                                                Authentication authentication) {
        String login = authentication.getName();
        return adminService.isEmployeeBlocked(employeeLogin, login);
    }
}
