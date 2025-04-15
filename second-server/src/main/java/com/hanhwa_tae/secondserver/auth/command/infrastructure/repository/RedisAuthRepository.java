package com.hanhwa_tae.secondserver.auth.command.infrastructure.repository;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.RefreshToken;
import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import org.springframework.data.repository.CrudRepository;

public interface RedisAuthRepository extends CrudRepository<RefreshToken, String> , AuthRepository {

}
