package com.infinity.nto.repository;

import com.infinity.nto.dto.EmployeeDataDto;
import com.infinity.nto.entity.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Long> {
    @Query("select count(e) > 0 from Employee e where e.login = :login and e.employeeData is not null")
    boolean existsByEmployeeLogin(@Param("login") String login);

    @Query("select new com.infinity.nto.dto.EmployeeDataDto(ed.name, ed.photo, ed.employeePosition, ed.lastVisit) " +
            "from Employee e join e.employeeData ed where e.login = :login")
    EmployeeDataDto getEmployeeDataDtoByLogin(@Param("login") String login);

    @Modifying
    @Query("update EmployeeData ed set ed.lastVisit = :time where ed.id = (select e.employeeData.id from Employee e where e.login = :login)")
    void updateLastVisitByEmployeeLogin(@Param("login") String login, @Param("time") LocalDateTime time);
}
