package com.hanhwa_tae.secondserver.travelmatepost.command.application.service;

import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.image.dto.request.SaveImageRequest;
import com.hanhwa_tae.secondserver.image.service.ImageService;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.TmpInsertRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.TmpUpdateRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.domain.aggregate.TravelMatePost;
import com.hanhwa_tae.secondserver.travelmatepost.command.domain.repository.JpaTravelMatePostRepository;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.RankType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TmpCmdService {

    private final ModelMapper modelMapper;
    private final JpaTravelMatePostRepository jpaTravelMatePostRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    /* 동행글 등록 */
    @Transactional
    public int createTmp(String id, TmpInsertRequest request) {

        TravelMatePost travelMatePost = modelMapper.map(request, TravelMatePost.class);
        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        travelMatePost.setUser(user);
        List<String> imageUrls = request.getImageUrls();

        if (!imageUrls.isEmpty()) {
            imageService.saveImage(SaveImageRequest.builder().imageList(imageUrls).build());
        }

        TravelMatePost saved = jpaTravelMatePostRepository.save(travelMatePost);

        return saved.getTravelMatePostId();
    }

    /* 동행글 수정 */
    @Transactional
    public void updatePost(String id, Integer travelMatePostId, TmpUpdateRequest request) {
        TravelMatePost travelMatePost = jpaTravelMatePostRepository.findById(travelMatePostId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!user.getUserNo().equals(travelMatePost.getUser().getUserNo())) {
            throw new BusinessException(ErrorCode.POST_NOT_OWNED);
        }

        List<String> imageUrls = request.getImageUrls();

        if (!imageUrls.isEmpty()) {
            imageService.saveImage(SaveImageRequest.builder().imageList(imageUrls).build());
        }

        travelMatePost.updateProductDetails(
                request.getTitle(),
                request.getContent()
        );
    }

    /* 동행글 삭제 */
    public void deletePost(String id, Integer travelMatePostId) {

        TravelMatePost travelMatePost = jpaTravelMatePostRepository.findById(travelMatePostId)
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!user.getUserNo().equals(travelMatePost.getUser().getUserNo()) && !user.getRank().getRankName().equals(RankType.SLAVE)) {
            throw new BusinessException(ErrorCode.POST_NOT_OWNED);
        }

        jpaTravelMatePostRepository.deleteById(travelMatePostId);
    }
}
