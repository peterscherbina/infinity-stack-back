package com.infinity.nto.repository;
import com.infinity.nto.dto.EmployeeDataDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test EmployeeDataRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EmployeeDataRepositoryTest {

    @Autowired
    private EmployeeDataRepository employeeDataRepository;

    private static final String EXISTING_LOGIN = "employee";
    private static final String NON_EXISTING_LOGIN = "non_employee";

    private static final String EXISTING_NAME = "semyon";

    @DisplayName("Test EmployeeDataRepository.existsByEmployeeLogin")
    @Test
    void testExistsByEmployeeLogin() {
        boolean exists = employeeDataRepository.existsByEmployeeLogin(EXISTING_LOGIN);
        boolean doesNotExist = employeeDataRepository.existsByEmployeeLogin(NON_EXISTING_LOGIN);

        assertThat(exists).isTrue();
        assertThat(doesNotExist).isFalse();
    }

    @DisplayName("Test EmployeeDataRepository.getEmployeeDataDtoByLogin")
    @Test
    void testGetEmployeeDataDtoByLogin() {
        EmployeeDataDto employeeDataDto = employeeDataRepository.getEmployeeDataDtoByLogin(EXISTING_LOGIN);

        assertThat(employeeDataDto).isNotNull();
        assertThat(employeeDataDto.getName()).isEqualTo(EXISTING_NAME);
    }

    @DisplayName("Test EmployeeDataRepository.updateLastVisitByEmployeeLogin")
    @Test
    void testUpdateLastVisitByEmployeeLogin() {
        LocalDateTime newTime = LocalDateTime.now();

        employeeDataRepository.updateLastVisitByEmployeeLogin(EXISTING_LOGIN, newTime);

        EmployeeDataDto updatedEmployeeDataDto = employeeDataRepository.getEmployeeDataDtoByLogin(EXISTING_LOGIN);
        assertThat(updatedEmployeeDataDto).isNotNull();
        assertThat(updatedEmployeeDataDto.getLastVisit().withNano(0)).isEqualTo(newTime.withNano(0));
    }
}
