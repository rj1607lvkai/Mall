package com.lk.service;

import com.lk.common.ServerResponse;
import com.lk.pojo.User;

public interface UserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkvalid(String str,String type);
    ServerResponse<String> selectQuestionByUsername(String username);
    ServerResponse<String> checkAnswer(String username,String question,String answer);
    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
    ServerResponse<User> updateInformation(User user);
    ServerResponse<User> getInformation(int id);
    ServerResponse checkAdminRole(User user);
}
