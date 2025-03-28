package com.infinity.nto.repository;

import com.infinity.nto.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test EmployeeRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final String EXISTING_LOGIN = "employee";
    private static final String NON_EXISTING_LOGIN = "non_employee";
    private static final boolean NEW_BLOCK_STATUS = true;

    @DisplayName("Test EmployeeRepository.existsByLogin")
    @Test
    void testExistsByLogin() {
        boolean exists = employeeRepository.existsByLogin(EXISTING_LOGIN);
        boolean doesNotExist = employeeRepository.existsByLogin(NON_EXISTING_LOGIN);

        assertThat(exists).isTrue();
        assertThat(doesNotExist).isFalse();
    }

    @DisplayName("Test EmployeeRepository.getIsBlockByLogin & EmployeeRepository.updateBlockConditionByLogin")
    @Test
    void testGetIsBlockByLoginAndUpdateBlockConditionByLogin() {
        boolean isBlocked = employeeRepository.getIsBlockByLogin(EXISTING_LOGIN);
        assertThat(isBlocked).isFalse();

        employeeRepository.updateBlockConditionByLogin(EXISTING_LOGIN, NEW_BLOCK_STATUS);
        boolean isBlockedNew = employeeRepository.getIsBlockByLogin(EXISTING_LOGIN);
        assertThat(isBlockedNew).isTrue();
    }

    @DisplayName("Test EmployeeRepository.findByLogin")
    @Test
    void testFindByLogin() {
        Optional<Employee> employee = employeeRepository.findByLogin(EXISTING_LOGIN);

        assertThat(employee).isPresent();
        assertThat(employee.get().getLogin()).isEqualTo(EXISTING_LOGIN);
    }
}
