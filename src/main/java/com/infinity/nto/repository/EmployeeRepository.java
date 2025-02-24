package com.infinity.nto.repository;

import com.infinity.nto.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select count(e) > 0 from Employee e where e.login = :login")
    boolean existsByLogin(@Param("login") String login);

    @Query("select e.isBlock from Employee e where e.login = :login")
    boolean getIsBlockByLogin(@Param("login") String login);

    @Modifying
    @Query("update Employee e set e.isBlock = :value where e.login = :login")
    void updateBlockConditionByLogin(@Param("login") String login, @Param("value") boolean value);

    Optional<Employee> findByLogin(String s);
}
