package dev.junah.spring_study.users.domain;

import dev.junah.spring_study.commom.domain.BaseEntity;
import dev.junah.spring_study.commom.permission.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(length = 127, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 127, nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "int default 0")
    @Builder.Default
    private int permission = Permission.USER.getBit();
}
