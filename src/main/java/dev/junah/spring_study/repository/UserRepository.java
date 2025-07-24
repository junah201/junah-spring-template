package dev.junah.spring_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.junah.spring_study.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
