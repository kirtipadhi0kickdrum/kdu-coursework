package com.kdu.smarthome.authcontroller;


import com.kdu.smarthome.authservice.AuthService;
import com.kdu.smarthome.request.RegistrationRequest;
import com.kdu.smarthome.response.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegistrationRequest request)
    {
        RegistrationResponse response = authService.registerUser(request);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", response.getMessage());
        responseBody.put("token", response.getToken());
        return ResponseEntity.ok(responseBody);
    }
}
