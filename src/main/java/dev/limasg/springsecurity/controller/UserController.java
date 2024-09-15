package dev.limasg.springsecurity.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.limasg.springsecurity.controller.dto.CreateUserDto;
import dev.limasg.springsecurity.controller.dto.MessageResponseDto;
import dev.limasg.springsecurity.entities.Role;
import dev.limasg.springsecurity.entities.User;
import dev.limasg.springsecurity.repository.RoleRepository;
import dev.limasg.springsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<MessageResponseDto> newUser(@RequestBody CreateUserDto dto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        var messageResponse = new MessageResponseDto("Usuário criado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
