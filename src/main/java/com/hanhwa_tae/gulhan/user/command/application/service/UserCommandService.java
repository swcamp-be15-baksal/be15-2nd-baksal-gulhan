package com.hanhwa_tae.gulhan.user.command.application.service;

import com.hanhwa_tae.gulhan.user.command.application.dto.UserCreateDTO;
import com.hanhwa_tae.gulhan.user.command.application.dto.UserInfoCreateDTO;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Rank;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RankType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.UserInfo;
import com.hanhwa_tae.gulhan.user.command.domain.repository.UserInfoRepository;
import com.hanhwa_tae.gulhan.user.command.domain.repository.UserRepository;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(@Valid UserCreateRequest request) {
        // 평민 등급 조회
        Rank defaultRank = userMapper.findRankIdByRankName(RankType.COMMONER.name());

        UserCreateDTO userDto = UserCreateDTO.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .userName(request.getUserName())
                .email(request.getEmail())
                .gender(request.getGender())
                .rankId(defaultRank.getRankId())
                .build();


        User user = modelMapper.map(userDto, User.class);
        user.setDefaultRank(defaultRank);
        user.setEncodedPassword(passwordEncoder.encode(request.getPassword()));

        /* 유저 저장 */
        userRepository.save(user);

        /* 유저 상세 정보 저장 */
        /* ??? 유저 상세 정보 저장을 따로 빼서 구현하기? */
        UserInfoCreateDTO userInfoDto = UserInfoCreateDTO.builder()
                .user(user)
                .birth(request.getBirth())
                .phone(request.getPhone())
                .address(request.getAddress())
                .countryCode(request.getCountryCode())
                .build();

        UserInfo userInfo = modelMapper.map(userInfoDto, UserInfo.class);
        userInfoRepository.save(userInfo);
    }
}
