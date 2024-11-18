package com.example.kanban.service;

import com.example.kanban.model.User;
import com.example.kanban.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    public String authenticate(String username, String password) {
        User user = (User) userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return generateToken(user);
        }
        throw new RuntimeException("Invalid credentials");
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
