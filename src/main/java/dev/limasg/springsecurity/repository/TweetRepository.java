package dev.limasg.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.limasg.springsecurity.entities.Tweet;

public interface TweetRepository extends JpaRepository <Tweet, Long> {

}
