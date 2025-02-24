package com.infinity.nto.repository;

import com.infinity.nto.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Modifying
    @Query(value = "insert into entry(employee_id, code_id, entry_time, is_card) values (:employeeId, :codeId, :entryTime, false)", nativeQuery = true)
    void insert(@Param("employeeId") long employeeId, @Param("codeId") long codeId, @Param("entryTime") LocalDateTime entryTime);

    List<Entry> findAllByEmployeeId(Long employeeId);
}
