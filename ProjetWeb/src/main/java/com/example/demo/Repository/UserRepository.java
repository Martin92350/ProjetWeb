package com.example.demo.Repository;

import com.example.demo.Model.Survey;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM auth_user WHERE email=?1", nativeQuery = true)
    public User getUserByEmail(String email);

    @Query(value = "SELECT first_name FROM auth_user WHERE auth_user_id=?1", nativeQuery = true)
    public String getUsernamebyId(int user_id);

    @Query(value = "SELECT auth_user.first_name FROM auth_user INNER JOIN survey ON auth_user.auth_user_id=survey.user_id", nativeQuery = true)
    public List<String> getAllCreators();

}
