package dev.limasg.springsecurity.controller.dto;

public record LoginResponse(String acessToken, Long expiresIn) {

}
