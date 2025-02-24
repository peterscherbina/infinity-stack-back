package com.infinity.nto.service.impl;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.dto.EntryDto;
import com.infinity.nto.dto.mapper.EmployeeDataMapper;
import com.infinity.nto.dto.mapper.EntryMapper;
import com.infinity.nto.entity.Employee;
import com.infinity.nto.entity.EmployeeData;
import com.infinity.nto.exception.EmployeeDataNotFoundException;
import com.infinity.nto.exception.EmployeeNotFoundException;
import com.infinity.nto.exception.SelfChangeException;
import com.infinity.nto.repository.EmployeeDataRepository;
import com.infinity.nto.repository.EmployeeRepository;
import com.infinity.nto.repository.EntryRepository;
import com.infinity.nto.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDataRepository employeeDataRepository;
    private final EntryRepository entryRepository;

    @Override
    public EmployeeDataDto getEmployeeInfo(String employeeLogin, String selfLogin) {
        if (employeeLogin.equals(selfLogin)) {
            throw new SelfChangeException("Self View");
        }

        Optional<Long> employee = employeeRepository.findIdByLogin(employeeLogin);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        Optional<EmployeeData> employeeData = employeeDataRepository.findByOwnerId(employee.get());
        if (employeeData.isEmpty()) {
            throw new EmployeeDataNotFoundException("Employee Data Not Found");
        }

        return EmployeeDataMapper.toEmployeeDataDto(employeeData.get());
    }

    @Transactional
    @Override
    public void setBlockCondition(String employeeLogin, boolean blockCondition, String selfLogin) {
        if (employeeLogin.equals(selfLogin)) {
            throw new SelfChangeException("Self Change");
        }

        Optional<Long> employee = employeeRepository.findIdByLogin(employeeLogin);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        employeeRepository.updateBlockCondition(employee.get(), blockCondition);
    }

    @Override
    public List<EntryDto> getEmployeeEntryList(String employeeLogin, String selfLogin) {
        if (employeeLogin.equals(selfLogin)) {
            throw new SelfChangeException("Self View");
        }

        Optional<Long> employee = employeeRepository.findIdByLogin(employeeLogin);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return entryRepository.findAllByEmployeeId(employee.get()).stream()
                .map(EntryMapper::toEntryDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmployeeBlocked(String employeeLogin, String selfLogin) {
        if (employeeLogin.equals(selfLogin)) {
            throw new SelfChangeException("Self View");
        }

        Optional<Employee> employee = employeeRepository.findByLogin(employeeLogin);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return employee.get().isBlock();
    }
}
