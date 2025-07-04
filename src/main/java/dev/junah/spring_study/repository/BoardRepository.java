package dev.junah.spring_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.junah.spring_study.domain.Board;

public interface BoardRepository extends JpaRepository<Board, String> {
}
