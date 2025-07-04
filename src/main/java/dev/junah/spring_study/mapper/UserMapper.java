package dev.junah.spring_study.mapper;

import dev.junah.spring_study.domain.User;
import dev.junah.spring_study.dto.auth.SignupReqDto;
import dev.junah.spring_study.dto.user.UserBaseUpdateDto;
import dev.junah.spring_study.dto.user.UserResDto;

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
