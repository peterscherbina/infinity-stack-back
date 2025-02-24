package com.infinity.nto.repository;

import com.infinity.nto.dto.EntryDto;
import com.infinity.nto.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    /*@Modifying
    @Query("insert into Entry (code, employee, entryTime, isCard) " +
            "values (select c from Code c where c.value = :value, " +
            "select e from Employee e where e.login = :login, time, isCard)")*/
    @Modifying
    @Query(value = "insert into entry (code_id, employee_id, entry_time, is_card) " +
                    "select c.id, e.id, :time, :is_card " +
                    "from code c, employee e " +
                    "where c.value = :value and e.login = :login",
            nativeQuery = true)
    void createEntry(@Param("login") String login,
                     @Param("value") Long value,
                     @Param("time") LocalDateTime time,
                     @Param("is_card") Boolean isCard);


    @Query("select new com.infinity.nto.dto.EntryDto(c.id, e.entryTime, e.isCard) " +
            "from Entry e join e.code c join e.employee emp where emp.login = :login")
    List<EntryDto> findEntriesByEmployeeLogin(@Param("login") String login);
}
