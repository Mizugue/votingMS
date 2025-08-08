package com.hallak.PollVotingService.OPF.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallak.shared_library.errorhandling.APIError;
import com.hallak.shared_library.exceptions.ResourceNotFoundException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {

            String body = Util.toString(response.body().asReader());
            APIError error = objectMapper.readValue(body, APIError.class);


            if (response.status() == 404) {
                return new ResourceNotFoundException();
            }

            return new RuntimeException("Err in the call " + methodKey + ": " + error.getError());

        } catch (Exception e) {
            return new RuntimeException("Erro ao processar erro do Feign", e);
        }
    }
}