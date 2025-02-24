package com.infinity.nto.repository;

import com.infinity.nto.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    @Query("select count(c) > 0 from Code c where c.value = :value")
    boolean existsByValue(@Param("value") Long value);

}
