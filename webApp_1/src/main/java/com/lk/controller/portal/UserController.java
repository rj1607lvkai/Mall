package com.lk.controller.portal;

import com.lk.common.Const;
import com.lk.common.ResponseCode;
import com.lk.common.ServerResponse;
import com.lk.pojo.User;


import com.lk.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping(value="/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="login.action",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response=userService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }


    @RequestMapping(value="logout.action")
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value="register.action")
    @ResponseBody
    public ServerResponse<String> register(User user){
        return userService.register(user);
    }


    @RequestMapping(value="check_valid.action")
    @ResponseBody
    public ServerResponse<String> checkvalid(String str,String type){
        return userService.checkvalid(str,type);
    }


    @RequestMapping(value="get_user_info.action")
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user!=null){
            return ServerResponse.createBySuccess(user);

        }
        else {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

    }
    @RequestMapping(value="forget_get_question.action")
    @ResponseBody
    public ServerResponse<String> forgrtGetQuestion(String username){
        return userService.selectQuestionByUsername(username);
    }
    @RequestMapping(value="forget_check_answer.action")
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return userService.checkAnswer(username,question,answer);
    }


    @RequestMapping(value="forget_reset_password.action")
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username,String password,String forgetToken){
        return userService.forgetResetPassword(username,password,forgetToken);
    }
    @RequestMapping(value="reset_password.action")
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return userService.resetPassword(passwordOld,passwordNew,user);
    }
    @RequestMapping(value="update_information.action")
    @ResponseBody
    public ServerResponse<String> update_information(HttpSession session,User updateUser){
        User currentUser=(User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser==null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        updateUser.setId(currentUser.getId());
        updateUser.setPassword(currentUser.getPassword());
        updateUser.setRole(currentUser.getRole());
        updateUser.setAnswer(currentUser.getAnswer());
        updateUser.setQuestion(currentUser.getQuestion());
        updateUser.setCreateTime(currentUser.getCreateTime());
        updateUser.setUsername(currentUser.getUsername());
       // SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updateUser.setUpdateTime(new Date());
        ServerResponse response=userService.updateInformation(updateUser);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    @RequestMapping(value="get_information.action")
    @ResponseBody
    public ServerResponse<User> get_information(HttpSession session){
        User currentUser=(User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录");

        }
        return userService.getInformation(currentUser.getId());
    }
}
