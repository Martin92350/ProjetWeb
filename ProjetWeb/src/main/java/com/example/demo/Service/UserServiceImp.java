package com.example.demo.Service;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    BCryptPasswordEncoder hash;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public void saveUser(User user) {
        user.setPassword(hash.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepo.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepo.save(user);
    }

    @Override
    public boolean doesUserExist(User user) {
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        User activeUser = userRepo.getUserByEmail(email);
        return activeUser;
    }

    @Override
    public String getUsernamebyId(int user_id) {
        String username = userRepo.getUsernamebyId(user_id);
        return username;
    }

    public List<String> getAllCreators() {
        List<String> creators = new ArrayList<>();
        creators = userRepo.getAllCreators();
        System.out.println("CREATORS == " + creators.toString());
        return creators;
    }

}
