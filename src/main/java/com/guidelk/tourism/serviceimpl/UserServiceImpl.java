package com.guidelk.tourism.serviceimpl;


import com.guidelk.tourism.entity.User;
import com.guidelk.tourism.repository.UserRepository;
import com.guidelk.tourism.service.UserService;
import com.guidelk.tourism.util.MasterDataStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity createUser(User user) {
        ResponseEntity responseEntity;
        User dbUser = this.userRepository.findByUserNameContainsIgnoreCaseAndStatusNot(user.getUserName(), MasterDataStatus.DELETED.getStatusSeq());
        User dbUserEmail = this.userRepository.findByEmailContainsIgnoreCaseAndStatusNot(user.getEmail(), MasterDataStatus.DELETED.getStatusSeq());
        if (dbUser != null) {
            responseEntity = new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        } else if (dbUserEmail != null) {
            responseEntity = new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        } else {
            String originalPassword = user.getPassword().trim();
            String password = "{bcrypt}" + BCrypt.hashpw(originalPassword, BCrypt.gensalt());
            user.setPassword(password);
            user.setUserName(user.getUserName().trim());
            user.setEmail(user.getEmail().toLowerCase().trim());
            this.userRepository.save(user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity updateUser(User user) {
        ResponseEntity responseEntity;
        Optional<User> dbUser = this.userRepository.findById(user.getUserId());
        if (dbUser.isPresent()) {
            String originalPassword = user.getPassword().trim();
            String password = "{bcrypt}" + BCrypt.hashpw(originalPassword, BCrypt.gensalt());
            if (dbUser.get().getUserName().equals(user.getUserName())) {
                responseEntity = new ResponseEntity<>(dbUser.get(), HttpStatus.NOT_MODIFIED);
            } else {
                user.setPassword(password);
                user.setUserName(user.getUserName().trim());
                user.setEmail(user.getEmail().toLowerCase().trim());
                this.userRepository.save(user);
                responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
            }
        } else {
            responseEntity = new ResponseEntity<>("Record not found", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @Override
    public User getUserData(String userName) {
        return this.userRepository.findByUserNameIgnoreCase(userName);
    }


}
