package com.test.validationassessment.controller;

import com.test.validationassessment.dto.SignupDTO;
import com.test.validationassessment.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

import static com.test.validationassessment.constant.Routes.AUTH_BASE_ROUTE;

@RequestMapping(AUTH_BASE_ROUTE)
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;

    //Question A: return validated fields
    @PostMapping("/validate")
    public ResponseEntity<Object> signup(@RequestBody SignupDTO signupDTO) throws ExecutionException, InterruptedException {
        Object response = authenticationService.validateFieldsAsync(signupDTO).get();
        if (response instanceof Boolean) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    //Question B & C: return JWT token
    @PostMapping("/generate-token")
    public ResponseEntity<String> generateJwtToken(@RequestBody SignupDTO dto) {
        String response = authenticationService.generateJwtToken(dto);
        return ResponseEntity.ok(response);
    }

    //Question D: verify JWT token
    @GetMapping("/verify-token")
    public ResponseEntity<String> verifyJwtToken(@RequestParam String accessToken) {
        boolean isValid = authenticationService.validateJwtToken(accessToken);
        if (isValid){
            return ResponseEntity.ok("verification pass");
        }
        return ResponseEntity.ok("verification fails");
    }
}
