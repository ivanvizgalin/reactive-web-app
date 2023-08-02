package com.example.reactive.util;

import com.example.reactive.model.User;
import com.example.reactive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitData {

    private final UserRepository userRepository;

    @PostConstruct
    void init () {
        userRepository.deleteAll().block();

        for (int i =0; i < 100; i++) {
            Mono<User> savedUser = userRepository.save(generatedRandomUser());
            savedUser.block();
        }
    }

    private User generatedRandomUser() {
        String randomUserId = UUID.randomUUID().toString();
        String randomUserEmail = "email" + randomUserId + "@mail.ru";
        return new User(randomUserId, randomUserEmail);
    }
}
