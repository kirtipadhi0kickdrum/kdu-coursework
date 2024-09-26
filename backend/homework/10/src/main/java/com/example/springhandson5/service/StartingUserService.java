package com.example.springhandson5.service;


import com.example.springhandson5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StartingUserService implements CommandLineRunner {


    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public StartingUserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception{
        userRepository.addUserData("Kirti", "Kirti Bijay Padhi", passwordEncoder.encode("@1234"), "kirtibijaypadhi.12@gmail.com", "ROLE_ADMIN");
    }
}
