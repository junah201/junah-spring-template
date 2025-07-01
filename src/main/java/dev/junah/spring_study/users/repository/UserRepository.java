package dev.junah.spring_study.users.repository;

import dev.junah.spring_study.users.domain.User;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Page<User> findAll(Pageable pageable);
}
