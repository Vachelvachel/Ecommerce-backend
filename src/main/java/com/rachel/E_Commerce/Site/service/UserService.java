package com.rachel.E_Commerce.Site.service;


import com.rachel.E_Commerce.Site.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User register(User user);
    public String login(String userName, String password);
}
