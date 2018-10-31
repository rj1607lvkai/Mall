package com.lk.controller.backend;

import com.lk.common.Const;
import com.lk.common.ResponseCode;
import com.lk.common.ServerResponse;
import com.lk.pojo.User;
import com.lk.service.CategoryService;
import com.lk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/manage/category/")
public class CategoryManageController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "addCategory.action")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return categoryService.addCategory(categoryName, parentId);

        } else {
            return ServerResponse.createByErrorMessage("不是管理员");
        }

    }

    @RequestMapping(value = "setCategoryName.action")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return categoryService.updateCategoryName(categoryId, categoryName);

        } else {
            return ServerResponse.createByErrorMessage("不是管理员");
        }
    }

    @RequestMapping(value = "getCategory.action")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return categoryService.getChildrenParallelCategory(categoryId);

        } else {
            return ServerResponse.createByErrorMessage("不是管理员");
        }
    }

    @RequestMapping(value = "getDeepCategory.action")
    @ResponseBody
    public ServerResponse getAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return categoryService.getAndDeepChildrenCategory(categoryId);

        } else {
            return ServerResponse.createByErrorMessage("不是管理员");
        }
    }
}