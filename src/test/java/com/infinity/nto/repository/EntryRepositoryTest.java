package com.infinity.nto.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test EntryRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class EntryRepositoryTest {
    @Autowired
    private EntryRepository entryRepository;

    private static final String LOGIN = "employee";
    private static final long CODE = 1234567890123456789L;
    private static final boolean IS_CARD = false;

    @DisplayName("Test EntryRepository.createEntry & EntryRepository.findEntriesByEmployeeLogin")
    @Test
    void testCreateEntryAndFindEntriesByEmployeeLogin() {
        LocalDateTime newTime = LocalDateTime.now();

        entryRepository.createEntry(LOGIN, CODE, newTime, IS_CARD);

        assertThat(entryRepository.findEntriesByEmployeeLogin(LOGIN).size() == 1).isTrue();

        entryRepository.createEntry(LOGIN, CODE, newTime, IS_CARD);
        entryRepository.createEntry(LOGIN, CODE, newTime, IS_CARD);
        entryRepository.createEntry(LOGIN, CODE, newTime, IS_CARD);
        entryRepository.createEntry(LOGIN, CODE, newTime, IS_CARD);

        assertThat(entryRepository.findEntriesByEmployeeLogin(LOGIN).size() == 5).isTrue();
    }
}
