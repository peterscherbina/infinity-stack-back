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
@Table(name = "employee_data")
public class EmployeeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "employee_position")
    private String employeePosition;

    @Column(name = "last_visit")
    private LocalDateTime lastVisit;
}
