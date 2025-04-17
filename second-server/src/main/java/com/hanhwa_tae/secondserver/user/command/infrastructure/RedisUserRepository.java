package com.hanhwa_tae.secondserver.user.command.infrastructure;

import com.hanhwa_tae.secondserver.user.command.domain.aggregate.RedisUser;
import org.springframework.data.repository.CrudRepository;


public interface RedisUserRepository extends CrudRepository<RedisUser, String> {

}
