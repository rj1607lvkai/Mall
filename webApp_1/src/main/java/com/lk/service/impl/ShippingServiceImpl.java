package com.lk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lk.common.ServerResponse;
import com.lk.dao.ShippingMapper;
import com.lk.pojo.Shipping;
import com.lk.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        Map result= Maps.newHashMap();
        int rowCount=shippingMapper.insert(shipping);
        if (rowCount>0){
            result.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功",result);
        }
        return ServerResponse.createByErrorMessage("新建地址失敗");
    }
    @Override
    public ServerResponse del(Integer shippingId,Integer userId){
        int resultCount=shippingMapper.deleteByShippingIdUserId(shippingId,userId);
        if (resultCount>0){
            return ServerResponse.createBySuccess("刪除地址成功");
        }
        return ServerResponse.createByErrorMessage("刪除地址失敗");
    }
    @Override
    public ServerResponse update(Integer userId, Shipping shipping){
        shipping.setUserId(userId);

        int rowCount=shippingMapper.update(shipping);
        if (rowCount>0){

            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失敗");

    }

    @Override
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId){


        Shipping shipping=shippingMapper.selectByShippingIdUserId(shippingId,userId);
        if (shipping != null){

            return ServerResponse.createBySuccess("查询成功",shipping);
        }
        return ServerResponse.createByErrorMessage("結果为空");

    }

    @Override
    public ServerResponse<PageInfo> list(Integer userId, Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList=shippingMapper.selectByUserId(userId);
        PageInfo pageInfo=new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);

    }
}
