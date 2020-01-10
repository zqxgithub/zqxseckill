package com.zqx.userModule.service;

import com.zqx.commom.entity.UserInfo;
import com.zqx.commom.form.UserRegisterForm;

public interface UserService {

    public UserInfo findUserById(int id);

    public void saveUserInfo(UserRegisterForm userVo) throws Exception;

    public boolean verifyUser(UserRegisterForm userVo) throws Exception;
}
