package com.hanhwa_tae.firstserver.goods.command.application.service;


import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import com.hanhwa_tae.firstserver.goods.command.domain.aggregate.Goods;
import com.hanhwa_tae.firstserver.goods.command.domain.aggregate.GoodsCategory;
import com.hanhwa_tae.firstserver.goods.command.domain.repository.JpaGoodsRepository;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsInsertRequest;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsUpdateRequest;
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
    private final EntityManager entityManager;

    @Transactional
    public int insertGoods( GoodsInsertRequest request) {
        Goods goods = modelMapper.map(request, Goods.class);

        GoodsCategory categoryRef = entityManager.getReference(GoodsCategory.class, request.getGoodsCategoryId());
        goods.setGoodsCategoryId(categoryRef);

        goods.setRemaining(goods.getQuantity());

        Goods newGoods = jpaGoodsRepository.save(goods);

        return newGoods.getGoodsId();
    }

    @Transactional
    public void updateGoods(Integer goodsId, GoodsUpdateRequest request) {
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
