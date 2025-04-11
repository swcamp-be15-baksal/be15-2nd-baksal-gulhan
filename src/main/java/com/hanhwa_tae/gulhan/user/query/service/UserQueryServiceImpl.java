package com.hanhwa_tae.gulhan.user.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.dto.request.LoginRequestUserRequest;
import com.hanhwa_tae.gulhan.user.query.dto.response.LoginRequestUserResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserMapper userMapper;

    @Override
    public UserInfoResponse getUserInfo(CustomUserDetail userDetail) {
        // TODO : .authenticated 사용 시 필요 없는지 확인하기!
        if(userDetail == null){
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }

        String userId = userDetail.getUserId();
        UserInfoResponse user = userMapper.findUserInfoByUserId(userId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );

        return user;
    }
}
