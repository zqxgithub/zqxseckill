package com.zqx.userModule.service.impl;

import com.zqx.commom.entity.UserInfo;
import com.zqx.commom.utils.Md5Util;
import com.zqx.commom.form.UserRegisterForm;
import com.zqx.userModule.mapper.UserMapper;
import com.zqx.userModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserInfo findUserById(int id) {
        UserInfo userById = userMapper.findUserById(id);
        return userById;
    }

    public void saveUserInfo(UserRegisterForm userVo) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setBirthday(userVo.getBirthday());
        userInfo.setUsername(userVo.getUsername());
        userInfo.setTelphone(userVo.getTelphone());
        userInfo.setQq(userVo.getQq());
        userInfo.setWeixin(userVo.getWeixin());
        userInfo.setOriginalPwd(userVo.getPassword());
        userInfo.setPassword(Md5Util.md5(userVo.getPassword(), Md5Util.slat));
        userMapper.saveUserInfo(userInfo);
    }

    public boolean verifyUser(UserRegisterForm userVo) throws Exception {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        String md5Password = userMapper.findUserByUsername(username).getPassword();//取出加密密码进行判断
        return Md5Util.verify(password, Md5Util.slat, md5Password);
    }
}
