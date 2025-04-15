package com.hanhwa_tae.firstserver.annotation.factory;

import com.hanhwa_tae.firstserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

public class WithCustomMockUserFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        String userId = annotation.userId();
        long userNo = annotation.userNo();
        String rank = annotation.rank();

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(rank));

        CustomUserDetail principal = new CustomUserDetail(
                userId,
                userNo,
                "mockPassword",
                authorities
        );

        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "mockPassword", authorities);
        context.setAuthentication(auth);
        return context;
    }
}
