package com.hallak.PollVotingService.OPF.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private final ObjectMapper objectMapper;


    public FeignConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


        @Bean
        public ErrorDecoder errorDecoder() {
            return new FeignErrorDecoder(objectMapper);
        }
    }