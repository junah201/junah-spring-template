package dev.junah.spring_study.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
