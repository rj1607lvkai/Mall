package com.lk.controller.portal;

import com.lk.common.Const;
import com.lk.common.ServerResponse;
import com.lk.pojo.User;
import com.lk.service.CartService;
import com.lk.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/cart/")


public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "add.action")
    @ResponseBody
    public ServerResponse<CartVo> add(HttpSession session, Integer count, Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.add(user.getId(),productId,count);
    }

    @RequestMapping(value = "update.action")
    @ResponseBody
    public ServerResponse update(HttpSession session,Integer count,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.update(user.getId(),productId,count);
    }
    @RequestMapping(value = "delete_product.action")
    @ResponseBody
        public ServerResponse<CartVo> deleteProduct(HttpSession session,String productIds){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.deleteProduct(user.getId(),productIds);
    }
    @RequestMapping(value = "list.action")
    @ResponseBody
        public ServerResponse<CartVo> list(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.list(user.getId());
    }
    @RequestMapping(value = "select_all.action")
    @ResponseBody
        public ServerResponse<CartVo> selectAll(HttpSession session,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.selectOrUnselect(user.getId(),Const.Cart.CHECKED,productId);
    }
    @RequestMapping(value = "un_select_all.action")
    @ResponseBody
        public ServerResponse<CartVo> unSelectAll(HttpSession session,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.selectOrUnselect(user.getId(),Const.Cart.UN_CHECKED,productId);
    }
    @RequestMapping(value = "select.action")
    @ResponseBody
        public ServerResponse<CartVo> select(HttpSession session,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.selectOrUnselect(user.getId(),Const.Cart.CHECKED,productId);
    }
    @RequestMapping(value = "un_select.action")
    @ResponseBody
        public ServerResponse<CartVo> unSelect(HttpSession session,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return cartService.selectOrUnselect(user.getId(),Const.Cart.UN_CHECKED,productId);
    }
    @RequestMapping(value = "get_product_count.action")
    @ResponseBody
        public ServerResponse<Integer> unSelect(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createBySuccess(0);
        }
        return cartService.getCartProductCount(user.getId());
    }
}

