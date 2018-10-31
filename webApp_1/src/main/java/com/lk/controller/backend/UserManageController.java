package com.lk.controller.backend;

import com.lk.common.Const;
import com.lk.common.ServerResponse;
import com.lk.pojo.User;
import com.lk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/manage/user/")
@ResponseBody
public class UserManageController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="login.action",method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response=userService.login(username,password);
        if(response.isSuccess()){
            User user=response.getData();
            if(user.getRole()==Const.Role.ROLE_ADMIN){

                session.setAttribute(Const.CURRENT_USER,response.getData());
                return response;
            }else {
                return ServerResponse.createByErrorMessage("不是管理员");
            }
        }
        return response;
    }
}
