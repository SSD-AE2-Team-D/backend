package com.guidelk.tourism.service;

import com.guidelk.tourism.entity.AuthRequest;
import org.springframework.stereotype.Component;

@Component
public interface LoginService {

    String authenticate(AuthRequest authRequest);
}
