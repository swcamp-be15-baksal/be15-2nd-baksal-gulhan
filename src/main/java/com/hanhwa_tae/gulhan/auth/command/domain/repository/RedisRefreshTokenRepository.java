package com.hanhwa_tae.gulhan.auth.command.domain.repository;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
