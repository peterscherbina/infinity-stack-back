package com.infinity.nto.controller;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.dto.EntryDto;
import com.infinity.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/auth")
    private void auth() {}

    @PostMapping("/info")
    private EmployeeDataDto info(Authentication authentication) {
        String login = authentication.getName();
        return employeeService.info(login);
    }

    @PostMapping("/open")
    private void open(@RequestParam("value") long value, Authentication authentication) {
        String login = authentication.getName();
        employeeService.open(login, value);
    }

    @PostMapping("/get-entry-list")
    private List<EntryDto> getEntryList(Authentication authentication) {
        String login = authentication.getName();
        return employeeService.getEntryList(login);
    }

    @PostMapping("/am-i-blocked")
    private boolean amIBlocked(Authentication authentication) {
        String login = authentication.getName();
        return employeeService.amIBlocked(login);
    }
}
