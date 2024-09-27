package com.example.springassessment.repository;

import com.example.springassessment.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllValidTokenByUser(Long id);
    Optional<Token> findByToken(String token);
}