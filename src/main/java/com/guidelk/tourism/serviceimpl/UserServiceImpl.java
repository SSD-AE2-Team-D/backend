package com.guidelk.tourism.serviceimpl;


import com.guidelk.tourism.entity.User;
import com.guidelk.tourism.repository.UserRepository;
import com.guidelk.tourism.service.UserService;
import com.guidelk.tourism.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity createUser(User user) {
        ResponseEntity responseEntity;
        User dbUser = this.userRepository.findByUserNameIgnoreCase(user.getUserName());
        User dbUserEmail = this.userRepository.findByEmailIgnoreCase(user.getEmail());
        if (dbUser != null) {
            responseEntity = new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        } else if (dbUserEmail != null) {
            responseEntity = new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        } else {
            String originalPassword = user.getPassword().trim();
            String password = "{bcrypt}" + BCrypt.hashpw(originalPassword, BCrypt.gensalt());
            user.setPassword(password);
            user.setUserName(user.getUserName().trim());
            user.setStatus(MasterDataStatus.APPROVED.getStatusSeq());
            this.userRepository.save(user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity updateUser(User user) {
        ResponseEntity responseEntity;
        User dbUser = this.userRepository.findByUserNameIgnoreCase(user.getUserName());
        if (dbUser.equals(user)) {
            responseEntity = new ResponseEntity<>(dbUser, HttpStatus.NOT_MODIFIED);
        } else {
            String originalPassword = user.getPassword().trim();
            String password = "{bcrypt}" + BCrypt.hashpw(originalPassword, BCrypt.gensalt());
            dbUser.setPassword(password);
            dbUser.setEmail(user.getEmail());
            dbUser.setMobileNo(user.getMobileNo());
            responseEntity = new ResponseEntity<>(dbUser, HttpStatus.CREATED);
        }

        return responseEntity;
    }
}
