package com.itsaur.fullstackexample.infrastructure.spring;

import com.itsaur.fullstackexample.domain.application.UserApplicationService;
import com.itsaur.fullstackexample.domain.model.UserRepository;
import com.itsaur.fullstackexample.infrastructure.jpa.JpaUserRepository;
import com.itsaur.fullstackexample.infrastructure.rest.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    UserApplicationService userApplicationService(UserRepository userRepository) {
        return new UserApplicationService(userRepository);
    }

    @Bean
    UserRepository userRepository() {
        return new JpaUserRepository();
    }

    @Bean
    UserController userController(UserApplicationService userApplicationService) {
        return new UserController(userApplicationService);
    }
}
