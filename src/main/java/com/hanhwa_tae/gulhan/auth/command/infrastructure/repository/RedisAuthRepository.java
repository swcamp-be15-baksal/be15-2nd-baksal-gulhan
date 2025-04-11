package com.hanhwa_tae.gulhan.auth.command.infrastructure.repository;

<<<<<<< HEAD
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import org.springframework.data.repository.CrudRepository;

public interface RedisAuthRepository extends CrudRepository<RefreshToken, String>, AuthRepository {
=======
import com.hanhwa_tae.gulhan.auth.command.application.dto.KakaoRefreshToken;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import org.springframework.data.repository.CrudRepository;

public interface RedisAuthRepository extends CrudRepository<KakaoRefreshToken, String> , AuthRepository {

>>>>>>> cd430ab (Feat: kakao 로그인/회원가입 기능 개발 #8)
}
