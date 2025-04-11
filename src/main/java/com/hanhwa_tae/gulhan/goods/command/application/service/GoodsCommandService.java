package com.hanhwa_tae.gulhan.goods.command.application.service;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.goods.command.domain.aggregate.Goods;
import com.hanhwa_tae.gulhan.goods.command.domain.aggregate.GoodsCategory;
import com.hanhwa_tae.gulhan.goods.command.domain.repository.JpaGoodsRepository;
import com.hanhwa_tae.gulhan.goods.query.dto.request.GoodsInsertRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoodsCommandService {
    private final ModelMapper modelMapper;
    private final JpaGoodsRepository jpaGoodsRepository;
    private EntityManager entityManager;

    @Transactional
    public int insertGoods(GoodsInsertRequest request) {
        Goods goods = modelMapper.map(request, Goods.class);
        Goods newGoods = jpaGoodsRepository.save(goods);

        return newGoods.getGoodsId();
    }

    @Transactional
    public void updateGoods(Integer goodsId, GoodsInsertRequest request) {
        Goods goods = jpaGoodsRepository.findById(goodsId).orElseThrow();
        GoodsCategory categoryRef = entityManager.getReference(GoodsCategory.class, request.getGoodsCategoryId());

        goods.updateGoods(
                request.getTitle(),
                request.getDetail(),
                request.getQuantity(),
                request.getPrice(),
                categoryRef
        );

    }

    @Transactional
    public void deleteGoods(Integer GoodsId) {
        Goods goods = jpaGoodsRepository.findById(GoodsId).orElseThrow();
        goods.setIsDeleted(DeleteType.Y);
    }
}
