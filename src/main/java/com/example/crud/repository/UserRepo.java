package com.example.crud.repository;

import com.example.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from user where email=?1", nativeQuery = true)
    User checkEmail(String email);

    @Query(value = "SELECT * from user where email=?1 and password=?2", nativeQuery = true)
    User checkLogin(String email, String password);

}
