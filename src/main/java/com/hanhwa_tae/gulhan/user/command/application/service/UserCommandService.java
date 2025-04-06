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
import com.hanhwa_tae.gulhan.utils.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtil emailUtil;

    @Transactional
    public void registerUser(@Valid UserCreateRequest request) {
        // TODO 검증 로직 수행


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



        /* TODO 유저 저장
         *  - Redis에 저장하기
         *      - uuid: {유저 정보 형태}
         *  - 이메일 보내기 */
        // 1. Redis에 데이터 저장

        // 2. 이메일 보내기
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>이메일 인증<h1>");

        String uuid = UUID.randomUUID().toString();
        sb.append("<h2>인증 코드 : ").append(uuid).append("<h2>");


        try {
            emailUtil.sendEmail(user.getEmail(), "[거한] 이메일 인증 입니다.", sb.toString());
        } catch (MessagingException e) {
            // TODO 오류 메시지 잡는거 생기면 수정하기
            e.printStackTrace();
        }

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
