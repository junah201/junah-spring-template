package dev.junah.spring_study.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.junah.spring_study.domain.Board;
import dev.junah.spring_study.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public Optional<Board> findById(String id) {
        return boardRepository.findById(id);
    }
}
