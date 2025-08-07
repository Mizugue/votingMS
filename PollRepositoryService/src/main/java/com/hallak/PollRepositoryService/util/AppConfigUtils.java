package com.hallak.PollRepositoryService.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigUtils {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
