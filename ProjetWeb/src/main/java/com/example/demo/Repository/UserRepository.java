package com.example.demo.Repository;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM auth_user WHERE email=?1", nativeQuery = true)
    public User getUserByEmail(String email);

    @Query(value = "SELECT first_name FROM auth_user WHERE auth_user_id=?1", nativeQuery = true)
    public String getUsernamebyId(int user_id);

}
