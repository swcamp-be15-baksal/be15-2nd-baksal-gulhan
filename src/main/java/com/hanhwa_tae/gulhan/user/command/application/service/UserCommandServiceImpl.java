package com.hanhwa_tae.gulhan.user.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.gulhan.user.command.application.dto.UserCreateDTO;
import com.hanhwa_tae.gulhan.user.command.application.dto.UserInfoCreateDTO;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.*;
import com.hanhwa_tae.gulhan.user.command.domain.repository.UserInfoRepository;
import com.hanhwa_tae.gulhan.user.command.domain.repository.UserRepository;
import com.hanhwa_tae.gulhan.user.command.infrastructure.RedisUserRepository;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import com.hanhwa_tae.gulhan.utils.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;    // 필요 없을지도..?
    private final EmailUtil emailUtil;
    private final ObjectMapper objectMapper;
    private final RedisUserRepository redisUserRepository;

    //    @Transactional
    public void registerUser(@Valid UserCreateRequest request) {
        User duplicateIdUser = userMapper.findUserByUserId(request.getUserId()).orElse(null);
        User duplicateEmailUser = userMapper.findUserByEmail(request.getEmail()).orElse(null);

        // 중복 유저가 존재할 경우
        if(duplicateIdUser != null){
            throw new RuntimeException("중복 아이디가 존재합니다!");
        }

        // 중복 이메일이 존재할 경우
        if(duplicateEmailUser != null){
            throw new RuntimeException("중복 이메일이 존재합니다!");
        }

        // 1. Redis에 데이터 저장
        String uuid = UUID.randomUUID().toString();

        request.setEncodedPassword(passwordEncoder.encode(request.getPassword()));

        // 1.2. redis에 유저 정보 저장
        try {
            String userData = objectMapper.writeValueAsString(request);
            RedisUser user = RedisUser.builder()
                    .uuid(uuid)
                    .userData(userData)
                    .build();
            redisUserRepository.save(user);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 2. 이메일 보내기
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>이메일 인증<h1>");
        sb.append("<h2>인증 코드 : ").append(uuid).append("<h2>");

        try {
            emailUtil.sendEmail(request.getEmail(), "[걸한] 이메일 인증 입니다.", sb.toString());
        } catch (MessagingException e) {
            // TODO 오류 메시지 잡는거 생기면 수정하기
            e.printStackTrace();
        }

        /* 이 아래는 verify 된 유저가 수행해야할 로직임 */
        // 평민 등급 조회
    }

    @Override
    @Transactional
    public void verifyByEmail(String uuid) {
        // 1. 일치하는 uuid가 redis에 존재하는지 확인
//        String userData = redisTemplate.opsForValue().get(uuid);
        RedisUser userData = redisUserRepository.findById(uuid).orElseThrow(
                () -> new RuntimeException("이메일 인증 시간이 만료되었습니다.")
        );

        // 2. redis 데이터 지워주기
        redisUserRepository.deleteById(uuid);
        UserCreateRequest userRequestDto = null;
        try {
            userRequestDto = objectMapper.readValue(userData.getUserData(), UserCreateRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // ! 이미 가입된 계정의 경우
        User duplicateUser = userMapper.findUserByUserId(userRequestDto.getUserId()).orElse(null);

        if(duplicateUser != null){
            throw new RuntimeException("이미 가입이 완료 되었습니다.");
        }

        // 2. DB에 value 값 가져와서 저장하기
        Rank defaultRank = userMapper.findRankIdByRankName(RankType.COMMONER.name());

        UserCreateDTO userDto = UserCreateDTO.builder()
                .userId(userRequestDto.getUserId())
                .password(userRequestDto.getPassword())
                .userName(userRequestDto.getUserName())
                .email(userRequestDto.getEmail())
                .gender(userRequestDto.getGender())
                .rankId((long) defaultRank.getRankId())
                .loginType(LoginType.GENERAL)
                .build();

        User user = modelMapper.map(userDto, User.class);
        user.setDefaultRank(defaultRank);

        userRepository.save(user);

        /* 유저 상세 정보 저장 */
        /* ??? 유저 상세 정보 저장을 따로 빼서 구현하기? */
        UserInfoCreateDTO userInfoDto = UserInfoCreateDTO.builder()
                .user(user)
                .birth(userRequestDto.getBirth())
                .phone(userRequestDto.getPhone())
                .address(userRequestDto.getAddress())
                .countryCode(userRequestDto.getCountryCode())
                .build();

        UserInfo userInfo = modelMapper.map(userInfoDto, UserInfo.class);
        userInfoRepository.save(userInfo);
    }
}
