package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository {

    @Query(value = "SELECT * FROM auth_user WHERE email=?1", nativeQuery = true)
    public User getUserByEmail(String email);

}
