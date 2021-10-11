package com.guidelk.tourism.serviceimpl;

import com.guidelk.tourism.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getTestMsg() {
        return "Docker Running.....";
    }
}
