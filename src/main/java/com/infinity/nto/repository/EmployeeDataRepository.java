package com.infinity.nto.repository;

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
    Optional<EmployeeData> findByOwnerId(long ownerId);
    @Query("select e.id from EmployeeData e where e.ownerId = :owner_id")
    Optional<Long> findIdByOwnerId(@Param("owner_id") long ownerId);

    @Modifying
    @Query("update EmployeeData e set e.lastVisit = :time where e.id = :id")
    void updateTimeById(@Param("id") long id, @Param("time") LocalDateTime lastVisit);
}
