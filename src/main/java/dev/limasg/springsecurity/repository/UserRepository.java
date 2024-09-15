package dev.limasg.springsecurity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.limasg.springsecurity.entities.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
