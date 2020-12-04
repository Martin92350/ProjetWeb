package com.example.demo.Service;

import com.example.demo.Model.User;

public interface UserService {

    public void saveUser(User user);

    public boolean doesUserExist(User user);

    public User getUserByEmail(String email);

    public String getUsernamebyId(int user_id);

}
