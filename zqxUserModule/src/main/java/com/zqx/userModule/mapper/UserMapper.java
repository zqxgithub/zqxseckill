package com.zqx.userModule.mapper;


import com.zqx.commom.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public UserInfo findUserById(int id);

    public void saveUserInfo(UserInfo userInfo);

    public UserInfo findUserByUsername(String username);
}
