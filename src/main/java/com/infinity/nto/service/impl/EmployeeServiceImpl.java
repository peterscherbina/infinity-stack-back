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

    @Transactional
    @Override
    public EmployeeDataDto info(String login) {
        if (employeeRepository.existsByLogin(login)) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        if (employeeDataRepository.existsByEmployeeLogin(login)) {
            throw new EmployeeDataNotFoundException("Employee Data Not Found");
        }

        return employeeDataRepository.getEmployeeDataDtoByLogin(login);
    }

    @Transactional
    @Override
    public void open(String login, long value) {
        if (employeeRepository.existsByLogin(login)) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        if (employeeDataRepository.existsByEmployeeLogin(login)) {
            throw new EmployeeDataNotFoundException("Employee Data Not Found");
        }

        if (codeRepository.existsByValue(value)) {
            throw new CodeNotFoundException("Code Not Found");
        }
        if (employeeRepository.getIsBlockByLogin(login)) {
            throw new EmployeeIsBlockedException("Employee Is Blocked");
        }

        employeeDataRepository.updateLastVisitByEmployeeLogin(login, LocalDateTime.now());
        entryRepository.createEntry(login, value, LocalDateTime.now(), false);
    }

    @Transactional
    @Override
    public List<EntryDto> getEntryList(String login) {
        if (employeeRepository.existsByLogin(login)) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return entryRepository.findEntriesByEmployeeLogin(login);
    }

    @Transactional
    @Override
    public boolean amIBlocked(String login) {
        if (employeeRepository.existsByLogin(login)) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }

        return employeeRepository.getIsBlockByLogin(login);
    }
}
