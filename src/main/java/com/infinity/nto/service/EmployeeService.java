package com.infinity.nto.service;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.dto.EntryDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDataDto info(String login);

    void open(String login, long value);

    List<EntryDto> getEntryList(String login);

    boolean amIBlocked(String login);
}
