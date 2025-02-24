package com.infinity.nto.service;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.dto.EntryDto;

import java.util.List;

public interface AdminService {
    EmployeeDataDto getEmployeeInfo(String employeeLogin, String selfLogin);
    void setBlockCondition(String employeeLogin, boolean blockCondition, String selfLogin);
    List<EntryDto> getEmployeeEntryList(String employeeLogin, String selfLogin);
    boolean isEmployeeBlocked(String employeeLogin, String selfLogin);
}
