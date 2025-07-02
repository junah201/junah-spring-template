package dev.junah.spring_study.users.service;

import dev.junah.spring_study.users.domain.User;
import dev.junah.spring_study.users.dto.UserBaseUpdateDto;
import dev.junah.spring_study.users.dto.UserResDto;
import dev.junah.spring_study.users.exception.UserNotFoundException;
import dev.junah.spring_study.users.mapper.UserMapper;
import dev.junah.spring_study.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserResDto getById(Long id) {
        User user = findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return userMapper.toDto(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserResDto> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).getContent().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResDto update(Long id, UserBaseUpdateDto userBaseUpdateDto) {
        User user = findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        userMapper.updateEntityFromDto(userBaseUpdateDto, user);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
