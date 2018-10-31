package com.lk.service;

import com.lk.common.ServerResponse;
import com.lk.vo.CartVo;

public interface CartService {
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> add(Integer userId,Integer productId,Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnselect(Integer userId,Integer checked,Integer productId);

    ServerResponse<Integer> getCartProductCount(Integer id);
}
