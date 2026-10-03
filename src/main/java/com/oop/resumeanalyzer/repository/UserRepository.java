package com.oop.resumeanalyzer.repository;

import com.oop.resumeanalyzer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
