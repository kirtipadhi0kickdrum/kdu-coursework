package com.kdu.smarthome.config;


import com.kdu.smarthome.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig  {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider)
    {
        this.jwtAuthenticationFilter= jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(req ->
                        req.requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("api/v1/user/house**")
                                .permitAll()
                                .requestMatchers("/api/v1/house/**")
                                .permitAll()
                                .requestMatchers("/api/v1/user/add-to-house/**")
                                .permitAll()
                                .requestMatchers("/api/v1/house/rooms/**")
                                .permitAll()
                                .requestMatchers("/api/v1/house/devices/**")
                                .permitAll()
                                .requestMatchers("/api/v1/devices/move/**")
                                .permitAll()
                                .requestMatchers("/api/v1/user/houses")
                                .permitAll()
                                .requestMatchers("/api/v1/house/details/**")
                                .permitAll()
                                .requestMatchers("/api/v1/house/update-address/**")
                                .permitAll()
                                .requestMatchers("/api/v1/inventory/**")
                                .permitAll()
                                .requestMatchers("/api/v1/house/add-device/**")
                                .permitAll()
                                .requestMatchers("/api/v1/device/register/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())

                .sessionManagement(sessiom -> sessiom.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();


    }
}








