package com.example.springhandson5.authentication;

import com.example.springhandson5.dto.UserDTO;
import com.example.springhandson5.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    UserService userService;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        logger.info("Attempting authentication for user: {}", username);

        UserDTO user = userService.getUserDataByName(username);

        if(user == null)
        {
            throw new BadCredentialsException("No user found with the provided credentials");
        }
        else {
            if(passwordEncoder.matches(password, user.getPassword()))
            {
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(user.getRole()));

            }
            else {
                throw new BadCredentialsException("Invalid password entered>>");
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role)
    {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
}
