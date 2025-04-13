package com.hanhwa_tae.gulhan.auth.command.infrastructure.repository;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.KakaoRefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisKakaoAuthRepository extends CrudRepository<KakaoRefreshToken, String>  {
}
