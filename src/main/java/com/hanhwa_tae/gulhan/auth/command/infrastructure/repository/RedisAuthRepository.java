package com.hanhwa_tae.gulhan.auth.command.infrastructure.repository;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.KakaoRefreshToken;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import org.springframework.data.repository.CrudRepository;

public interface RedisAuthRepository extends CrudRepository<KakaoRefreshToken, String> , AuthRepository {

}
