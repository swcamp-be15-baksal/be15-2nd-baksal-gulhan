package com.hanhwa_tae.gulhan.user.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findUserByUserId(String userId);

    Optional<User> findUserByUserIdAndEmail(String userId, String email);
}
