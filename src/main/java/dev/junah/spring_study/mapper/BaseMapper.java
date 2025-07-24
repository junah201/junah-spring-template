package dev.junah.spring_study.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapping;
import org.mapstruct.Named;

public interface BaseMapper<E, D> {
    @Named("longToString")
    static String longToString(Long longValue) {
        return longValue == null ? null : longValue.toString();
    }

    @Named("localDateTimeToString")
    static String localDateTimeToString(LocalDateTime time) {
        return time == null ? null : time.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Mapping(source = "id", target = "id", qualifiedByName = "longToString")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localDateTimeToString")
    D toDto(E entity);
}
