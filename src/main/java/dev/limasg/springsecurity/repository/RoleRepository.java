package dev.limasg.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.limasg.springsecurity.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
