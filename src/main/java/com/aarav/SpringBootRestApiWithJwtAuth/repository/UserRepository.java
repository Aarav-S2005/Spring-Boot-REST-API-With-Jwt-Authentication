package com.aarav.SpringBootRestApiWithJwtAuth.repository;

import com.aarav.SpringBootRestApiWithJwtAuth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String username);
    public boolean existsUserByUsername(String username);
}
