package com.infinity.nto.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test CodeRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CodeRepositoryTest {
    @Autowired
    private CodeRepository codeRepository;

    private static final long CODE_FIRST = 1234567890123456789L;
    private static final long CODE_SECOND = 111;


    @DisplayName("Test CodeRepository.existsByValue")
    @Test
    void testExistsByValue() {
        assertThat(codeRepository.existsByValue(CODE_FIRST)).isEqualTo(true);
        assertThat(codeRepository.existsByValue(CODE_SECOND)).isEqualTo(false);
    }
}
