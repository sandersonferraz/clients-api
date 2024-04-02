package com.dev.san.model.repository;


import com.dev.san.model.entity.auth.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    UserDetails findByLogin(String login);
}
