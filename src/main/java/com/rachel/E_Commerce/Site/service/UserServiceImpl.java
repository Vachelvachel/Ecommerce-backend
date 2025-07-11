package com.rachel.E_Commerce.Site.service;

import com.rachel.E_Commerce.Site.model.User;
import com.rachel.E_Commerce.Site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        if(userRepository.findByUserName(user.getUserName())!=null){
            throw new RuntimeException("The username already exists!");
        }
        if(user.getPassword().length()<6){
            throw new RuntimeException("The password is too short");
        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoded = encoder.encode(user.getPassword());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if(user ==null){
            throw new RuntimeException("The user not exists");
        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoded = encoder.encode(user.getPassword());
//        if(!encoded.equals(user.getPassword())){
//            throw new RuntimeException("The password is false!");
//        }
        return user;
    }
}
