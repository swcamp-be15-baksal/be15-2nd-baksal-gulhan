package com.hanhwa_tae.secondserver.annotation;

import com.hanhwa_tae.secondserver.annotation.factory.WithCustomMockUserFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserFactory.class)
public @interface WithCustomMockAdmin {
    String userId() default "user02";

    long userNo() default 2;

    String rank() default "SLAVE";
}
