package com.hee.service;

import com.hee.config.JwtProvider;
import com.hee.model.User;
import com.hee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

//    Method find user by jwt token
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
       String email = jwtProvider.getEmailFromJwtToken(jwt);
       User user = findUserByEmail(email);
       return user;
    }

//    Method find user by email
    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new Exception("User not found!");
        }

        return user;
    }
}
