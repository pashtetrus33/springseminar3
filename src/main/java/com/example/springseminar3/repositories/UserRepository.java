package com.example.springseminar3.repositories;

import com.example.springseminar3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
