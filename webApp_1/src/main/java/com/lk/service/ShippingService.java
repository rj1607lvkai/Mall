package com.lk.service;

import com.github.pagehelper.PageInfo;
import com.lk.common.ServerResponse;
import com.lk.pojo.Shipping;
import com.lk.vo.CartVo;

public interface ShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse del(Integer shippingId,Integer userId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, Integer pageNum, Integer pageSize);
}
