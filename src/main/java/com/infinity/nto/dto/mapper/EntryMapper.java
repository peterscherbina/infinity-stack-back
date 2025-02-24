package com.infinity.nto.dto.mapper;

import com.infinity.nto.dto.EntryDto;
import com.infinity.nto.entity.Entry;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntryMapper {
    public static EntryDto toEntryDto(Entry entry) {
        return EntryDto.builder()
                //.codeId(entry.getCodeId())
                .entryTime(entry.getEntryTime())
                .isCard(entry.isCard())
                .build();
    }
}
