package com.hanhwa_tae.gulhan.auth.command.infrastructure.repository;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisAuthRepository extends CrudRepository<RefreshToken, String> {
}
