package com.hanhwa_tae.firstserver.annotation;

import com.hanhwa_tae.firstserver.annotation.factory.WithCustomMockUserFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserFactory.class)
public @interface WithCustomMockUser {

    String userId() default "user01";

    long userNo() default 1;

    String rank() default "COMMONER";
}
