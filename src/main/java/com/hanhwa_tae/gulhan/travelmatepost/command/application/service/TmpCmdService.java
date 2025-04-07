package com.hanhwa_tae.gulhan.travelmatepost.command.application.service;

import com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate.TravelMatePost;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository.JpaTravelMatePostRepository;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository.JpaUserRepository;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.TmpInsertRequest;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TmpCmdService {

    private final ModelMapper modelMapper;
    private final JpaTravelMatePostRepository  jpaTravelMatePostRepository;
    private final JpaUserRepository jpaUserRepository;

    /* 상품 등록 */
        public int createTmp(TmpInsertRequest request) {

            TravelMatePost travelMatePost = modelMapper.map(request, TravelMatePost.class);
            User user = jpaUserRepository.findById(request.getUserNo())
                    .orElseThrow(() -> new RuntimeException("사용자 없음"));
            travelMatePost.setUser(user);

            TravelMatePost saved = jpaTravelMatePostRepository.save(travelMatePost);

            return saved.getTravelMatePostId();


    }
}
