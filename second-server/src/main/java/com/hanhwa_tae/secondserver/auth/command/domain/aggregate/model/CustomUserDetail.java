package com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
public class CustomUserDetail implements UserDetails {
    private final String userId;
    private final Long userNo;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;


    @Override
    public String getUsername() {
        return this.userId;
    }

    /* 계정, 비밀번호 등의 만료를 관리하지 않으므로 일단 True로 해놓음 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
