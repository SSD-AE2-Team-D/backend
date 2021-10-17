package com.guidelk.tourism.service;

import com.guidelk.tourism.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ResponseEntity createUser(User user);

    ResponseEntity updateUser(User user);
}
