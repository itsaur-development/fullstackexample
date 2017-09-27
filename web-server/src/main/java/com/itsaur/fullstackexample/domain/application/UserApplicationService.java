package com.itsaur.fullstackexample.domain.application;

import com.google.common.base.Preconditions;
import com.itsaur.fullstackexample.domain.model.UserRepository;

public class UserApplicationService {

    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        Preconditions.checkNotNull(userRepository, "userRepository cannot be null");

        this.userRepository = userRepository;
    }
}
