package com.hanhwa_tae.secondserver.user.command.infrastructure;

import com.hanhwa_tae.secondserver.user.command.domain.aggregate.RedisUserPassword;
import org.springframework.data.repository.CrudRepository;

public interface RedisUserPasswordRepository extends CrudRepository<RedisUserPassword, String> {
}
