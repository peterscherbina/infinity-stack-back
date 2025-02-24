package com.infinity.nto.service.impl;

import com.infinity.nto.entity.Employee;
import com.infinity.nto.exception.EmployeeNotFoundException;
import com.infinity.nto.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByLogin(s);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return employee.get();
    }
}
