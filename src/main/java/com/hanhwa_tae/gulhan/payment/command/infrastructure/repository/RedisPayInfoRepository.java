package com.hanhwa_tae.gulhan.payment.command.infrastructure.repository;

import com.hanhwa_tae.gulhan.payment.command.domain.aggregate.Redispayinfo;
import org.springframework.data.repository.CrudRepository;

public interface RedisPayInfoRepository extends CrudRepository<Redispayinfo,String> {
}
