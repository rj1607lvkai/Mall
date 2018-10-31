package com.lk.controller.portal;

import com.github.pagehelper.PageInfo;
import com.lk.common.Const;
import com.lk.common.ServerResponse;
import com.lk.pojo.Shipping;
import com.lk.pojo.User;
import com.lk.service.ShippingService;
import com.lk.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/shipping/")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @RequestMapping(value = "add.action")
    @ResponseBody
    public ServerResponse<CartVo> add(HttpSession session, Shipping shipping){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return shippingService.add(user.getId(),shipping);
    }

    @RequestMapping(value = "del.action")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer shippingId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return shippingService.del(shippingId,user.getId());
    }

    @RequestMapping(value = "update.action")
    @ResponseBody
    public ServerResponse update(HttpSession session,Shipping shipping){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return shippingService.update(user.getId(),shipping);
    }
    @RequestMapping(value = "select.action")
    @ResponseBody
    public ServerResponse<Shipping> select(HttpSession session,Integer shippingId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return shippingService.select(user.getId(),shippingId);
    }

    @RequestMapping(value = "list.action")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return shippingService.list(user.getId(),pageNum,pageSize);
    }
}
