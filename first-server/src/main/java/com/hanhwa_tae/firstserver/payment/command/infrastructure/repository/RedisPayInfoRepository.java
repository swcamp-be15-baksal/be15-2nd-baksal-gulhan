package com.hanhwa_tae.firstserver.payment.command.infrastructure.repository;

import com.hanhwa_tae.firstserver.payment.command.domain.aggregate.Redispayinfo;
import org.springframework.data.repository.CrudRepository;

public interface RedisPayInfoRepository extends CrudRepository<Redispayinfo,String> {
}
