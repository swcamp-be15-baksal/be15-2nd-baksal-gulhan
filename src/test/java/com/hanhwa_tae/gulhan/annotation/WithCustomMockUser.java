package com.hanhwa_tae.gulhan.annotation;


import com.hanhwa_tae.gulhan.annotation.factory.WithCustomMockUserFactory;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RankType;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserFactory.class)
public @interface WithCustomMockUser {

    String userId() default "user01";

    long userNo() default 12;

    String rank() default "COMMONER";
}
