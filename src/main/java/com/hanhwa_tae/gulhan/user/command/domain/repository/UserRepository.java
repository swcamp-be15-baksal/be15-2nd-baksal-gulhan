package com.hanhwa_tae.gulhan.user.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

<<<<<<< HEAD
    Optional<User> findUserByUserId(String username);
=======
    Optional<User> findByUserId(String userId);

    User save(User product);

>>>>>>> cd430ab (Feat: kakao 로그인/회원가입 기능 개발 #8)
}
