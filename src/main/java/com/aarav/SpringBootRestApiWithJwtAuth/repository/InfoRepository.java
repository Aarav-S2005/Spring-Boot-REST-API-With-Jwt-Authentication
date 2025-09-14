package com.aarav.SpringBootRestApiWithJwtAuth.repository;

import com.aarav.SpringBootRestApiWithJwtAuth.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InfoRepository extends JpaRepository<Info, Integer> {

    @Query("SELECT i FROM Info i WHERE i.user.username = :username")
    public Info findInfoByUsername(@Param("username") String username);

    boolean existsByEmail(String email);
}
