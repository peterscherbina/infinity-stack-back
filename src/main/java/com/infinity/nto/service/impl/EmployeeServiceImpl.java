package com.infinity.nto.service.impl;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.dto.EntryDto;
import com.infinity.nto.dto.mapper.EmployeeDataMapper;
import com.infinity.nto.dto.mapper.EntryMapper;
import com.infinity.nto.entity.Code;
import com.infinity.nto.entity.Employee;
import com.infinity.nto.entity.EmployeeData;
import com.infinity.nto.exception.CodeNotFoundException;
import com.infinity.nto.exception.EmployeeDataNotFoundException;
import com.infinity.nto.exception.EmployeeIsBlockedException;
import com.infinity.nto.exception.EmployeeNotFoundException;
import com.infinity.nto.repository.CodeRepository;
import com.infinity.nto.repository.EmployeeDataRepository;
import com.infinity.nto.repository.EmployeeRepository;
import com.infinity.nto.repository.EntryRepository;
import com.infinity.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDataRepository employeeDataRepository;
    private final CodeRepository codeRepository;
    private final EntryRepository entryRepository;

    @Override
    public EmployeeDataDto info(String login) {
        Optional<Long> employee = employeeRepository.findIdByLogin(login);
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
    public void open(String login, long value) {
        Optional<Employee> employee = employeeRepository.findByLogin(login);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        if (employee.get().isBlock()) {
            throw new EmployeeIsBlockedException("Employee Is Blocked");
        }

        Optional<Long> employeeData = employeeDataRepository.findIdByOwnerId(employee.get().getId());
        if (employeeData.isEmpty()) {
            throw new EmployeeDataNotFoundException("Employee Data Not Found");
        }

        Optional<Code> code = codeRepository.findByValue(value);
        if (code.isEmpty()) {
            throw new CodeNotFoundException("Code Not Found");
        }


        employeeDataRepository.updateTimeById(employeeData.get(), LocalDateTime.now());

        entryRepository.insert(employee.get().getId(), code.get().getId(), LocalDateTime.now());
    }

    @Override
    public List<EntryDto> getEntryList(String login) {
        Optional<Long> employee = employeeRepository.findIdByLogin(login);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return entryRepository.findAllByEmployeeId(employee.get()).stream()
                .map(EntryMapper::toEntryDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean amIBlocked(String login) {
        Optional<Employee> employee = employeeRepository.findByLogin(login);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return employee.get().isBlock();
    }
}
