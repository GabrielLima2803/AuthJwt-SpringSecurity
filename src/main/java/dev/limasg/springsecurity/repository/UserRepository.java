package dev.limasg.springsecurity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.limasg.springsecurity.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
