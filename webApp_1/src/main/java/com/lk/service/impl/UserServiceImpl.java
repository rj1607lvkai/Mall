package com.lk.service.impl;

import com.lk.common.Const;
import com.lk.common.ServerResponse;
import com.lk.common.TokenCache;
import com.lk.dao.UserMapper;
import com.lk.pojo.User;
import com.lk.service.UserService;
import com.lk.utils.MD5Utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse serverResponse=this.checkvalid(user.getUsername(),Const.USERNAME);
        if(!serverResponse.isSuccess()){
            return serverResponse;
        }
        serverResponse=this.checkvalid(user.getEmail(),Const.EMAIL);
        if(!serverResponse.isSuccess()){
            return serverResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Utils.md5(user.getPassword()));
        int resultCount=userMapper.insert(user);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<User> login(String username, String password){
        System.out.println(1);
        int resultCount=userMapper.checkUsername(username);
        System.out.println(2);
        if(resultCount==0){
            System.out.println(3);
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        System.out.println(4);
        password=MD5Utils.md5(password);
        System.out.println(5);
        User user=userMapper.selectLogin(username,password);
        System.out.println(6);
        if(user==null){
            System.out.println(7);
            return ServerResponse.createByErrorMessage("用户名或密码错误");

        }
        System.out.println(8);
        user.setPassword(null);
        System.out.println(9);
        /*org.apache.commons.lang3.StringUtils.EMPTY*/
        return ServerResponse.createBySuccess("登陆成功",user);

    }
    @Override
    public ServerResponse<String> checkvalid(String str,String type){
        if(type !=null) {
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount != 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount != 0) {
                    return ServerResponse.createByErrorMessage("邮箱已使用");
                }
            }
            return ServerResponse.createBySuccessMessage("校验成功");
        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }

    }
    @Override
    public ServerResponse<String> selectQuestionByUsername(String username){
        ServerResponse validResponse=this.checkvalid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question=userMapper.selectQuestionByUsername(username);
        if(question!=null){
            return ServerResponse.createBySuccess(question);
        }
        else{
            return ServerResponse.createByErrorMessage("找回密码的问题不存在");
        }

    }
    @Override
    public ServerResponse<String> checkAnswer(String username,String question,String answer){
        int resultCount=userMapper.checkAnswer(username,question,answer);
        if(resultCount>0){
            String forgetToken= UUID.randomUUID().toString();
//            TokenCache.setKey("token_"+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        else {
            return ServerResponse.createByErrorMessage("问题的答案错误");
        }
    }
    @Override
    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
        /*if(forgetToken==null){
            return ServerResponse.createByErrorMessage("forgetToken需要传递");
        }*/
        ServerResponse validResponse=this.checkvalid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String password=MD5Utils.md5(passwordNew);
        int rowCount=userMapper.updatePasswordByUsername(username,password);
        if(rowCount>=0){
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
        else{
            return ServerResponse.createByErrorMessage("错误");
        }



    }
    @Override
    public ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user){
        int resultCount=userMapper.checkPassword(user.getId(),MD5Utils.md5(passwordOld));
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        else{
            int updateCount=userMapper.updatePasswordById(user.getId(),MD5Utils.md5(passwordNew));
            if(updateCount>0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }
        return ServerResponse.createByErrorMessage("密码修改失败");
    }
    @Override
    public ServerResponse<User> updateInformation(User updateUser){
        int resultCount=userMapper.checkEmailById(updateUser.getId(),updateUser.getEmail());
        if(resultCount>0){
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }

        int updateCount=userMapper.updateUserInfo(updateUser);
        if(updateCount>0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");

    }
    @Override
    public ServerResponse<User> getInformation(int id){
        User user=(User)userMapper.selectById(id);
        if(user==null){
            return ServerResponse.createByErrorMessage("找不到用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }
    @Override
    public ServerResponse checkAdminRole(User user){
        if(user != null && user.getRole() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByError();
        }
    }
}
