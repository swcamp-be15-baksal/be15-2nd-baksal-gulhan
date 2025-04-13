package com.hanhwa_tae.gulhan.user.command.infrastructure;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RedisUserId;
import org.springframework.data.repository.CrudRepository;

public interface RedisUserIdRepository extends CrudRepository<RedisUserId, String> {
}
