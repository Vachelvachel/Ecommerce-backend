package com.rachel.E_Commerce.Site.service;

import com.rachel.E_Commerce.Site.model.User;
import com.rachel.E_Commerce.Site.repository.UserRepository;
import com.rachel.E_Commerce.Site.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User register(User user) {
        if(userRepository.findByUserName(user.getUserName()).isPresent()){
            throw new RuntimeException("The username already exists!");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("The email already exists!");
        }
        if(user.getPassword()==null){
            throw new RuntimeException("The password cannot be empty!");
        }
        if(user.getPassword().length()<6){
            throw new RuntimeException("The password is too short");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(user.getPassword());
        user.setPassword(encoded);
        return userRepository.save(user);
    }

    @Override
    public String login(String userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
        if(user ==null){
            throw new RuntimeException("The user not exists");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("The password is false!");
        }
        return jwtUtil.generateToken(userName);
    }
}
