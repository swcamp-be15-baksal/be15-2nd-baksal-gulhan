package com.hanhwa_tae.gulhan.user.command.infrastructure;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RedisUser;
import org.springframework.data.repository.CrudRepository;


public interface RedisUserRepository extends CrudRepository<RedisUser, String> {

}
