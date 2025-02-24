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
    @Query("select e.id from Employee e where e.login = :login")
    Optional<Long> findIdByLogin(@Param("login") String login);

    Optional<Employee> findByLogin(String login);

    @Modifying
    @Query("update Employee set isBlock = :value where id = :id")
    void updateBlockCondition(@Param("id") long id, @Param("value") boolean value);
}
