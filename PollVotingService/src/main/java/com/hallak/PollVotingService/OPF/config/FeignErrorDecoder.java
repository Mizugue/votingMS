package com.hallak.PollVotingService.OPF.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hallak.shared_library.errorhandling.APIError;
import com.hallak.shared_library.exceptions.ResourceNotFoundException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Autowired
    public FeignErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {

            String body = Util.toString(response.body().asReader());
            APIError objectError = objectMapper.readValue(body, APIError.class);


            if (response.status() == 404) {
                return new ResourceNotFoundException(objectError);
            }

            return new RuntimeException("Err in the call " + methodKey);

        } catch (Exception e) {
            return new RuntimeException("Erro ao processar erro do Feign", e);
        }
    }
}