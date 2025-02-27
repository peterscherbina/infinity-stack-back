package com.infinity.nto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entry")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "code_id",
            referencedColumnName = "id"
    )
    private Code code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id"
    )
    private Employee employee;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "is_card")
    private boolean isCard;
}
