package com.infinity.nto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password_hashed")
    private String passwordHashed;

    @Column(name = "is_block")
    private boolean isBlock;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "employee_data_id",
            referencedColumnName = "id"
    )
    private EmployeeData employeeData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Entry> entries;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "relationship_employee_and_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordHashed;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
