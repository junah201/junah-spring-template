package dev.junah.spring_study.users.mapper;

import dev.junah.spring_study.auth.dto.SignupReqDto;
import dev.junah.spring_study.users.domain.User;
import dev.junah.spring_study.users.dto.UserBaseUpdateDto;
import dev.junah.spring_study.users.dto.UserResDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResDto toDto(User user);

    User toEntity(UserResDto userResDto);

    User toEntity(SignupReqDto signupReqDto);

    void updateEntityFromDto(UserBaseUpdateDto userUpdateDto, @MappingTarget User user);
}
