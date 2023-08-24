package com.test.validationassessment.service;
import com.test.validationassessment.dto.SignupDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.concurrent.CompletableFuture;

public interface AuthenticationService {
    CompletableFuture<Object> validateFieldsAsync(SignupDTO dto);

    String generateJwtToken(SignupDTO dto);

    boolean validateJwtToken(String token);

    String getJwtFromRequest(HttpServletRequest request);
}
