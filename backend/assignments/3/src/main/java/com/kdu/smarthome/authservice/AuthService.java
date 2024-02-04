package com.kdu.smarthome.authservice;


import com.kdu.smarthome.custom.CustomUserDetails;
import com.kdu.smarthome.entity.RoleEntity;
import com.kdu.smarthome.entity.UserEntity;
import com.kdu.smarthome.repository.RoleRepository;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.request.RegistrationRequest;
import com.kdu.smarthome.response.RegistrationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationResponse registerUser(RegistrationRequest request) {
        // Check if the username is already taken
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Check if the email is already taken
        if (userRepository.existsByEmailId(request.getEmailId())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Create a new user entity
        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailId(request.getEmailId())
                .roles(Collections.singletonList(getUserRole()))
                .build();

        // Save the user entity to the database
        userRepository.save(userEntity);

        // Generate a token for the user
        CustomUserDetails userDetails = new CustomUserDetails(userEntity);
        String token = jwtService.generateToken(userDetails);

        // Create a response with the token
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("User registered successfully");
        response.setToken(token);

        return response;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the given username"));

        return new CustomUserDetails(userEntity);
    }

    private RoleEntity getUserRole() {
        return roleRepository.findByName(RoleEntity.UserRole.valueOf("USER"))
                .orElseThrow(() -> new IllegalStateException("Default role 'USER' not found"));
    }
}
