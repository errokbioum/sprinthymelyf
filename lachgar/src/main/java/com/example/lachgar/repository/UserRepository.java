package com.example.lachgar.repository;

import com.example.lachgar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    @Query(value = "SELECT * FROM user u WHERE u.name =:name", nativeQuery = true)
    List<User> findUsersByName(@Param("name") String name);


}