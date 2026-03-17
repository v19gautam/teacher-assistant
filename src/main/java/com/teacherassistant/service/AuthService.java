package com.teacherassistant.service;

import com.teacherassistant.dto.LoginRequest;
import com.teacherassistant.dto.RegisterRequest;
import com.teacherassistant.model.User;
import com.teacherassistant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            return "Email already registered";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("TEACHER");

        userRepository.save(user);

        return "User registered successfully";
    }

    public Map<String, Object> login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("userId", user.getId());

        return response;
    }
}
