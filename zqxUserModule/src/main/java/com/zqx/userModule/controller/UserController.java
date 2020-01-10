package com.zqx.userModule.controller;

import com.zqx.commom.entity.UserInfo;
import com.zqx.commom.form.UserRegisterForm;
import com.zqx.userModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userControl")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("findUserById")
    public String findUserById(int id, Model model) {
        UserInfo userById = userService.findUserById(id);
        model.addAttribute("userInfo", userById);
        return "viewUser";
    }

    @GetMapping("toRegisterUser")
    public String toRegisterUser() {
        return "toRegisterUser";
    }


    @PostMapping("registerUser")
    public String registerUser(UserRegisterForm userVo, Model model) {
        if (StringUtils.isEmpty(userVo.getUsername())) {
            model.addAttribute("error", "username null error");
            return "toRegisterUser";
        }
        if (StringUtils.isEmpty(userVo.getPassword()) || StringUtils.isEmpty(userVo.getRepassword())) {
            model.addAttribute("error", "密码不能为空");
            return "toRegisterUser";
        }
        if (!userVo.getPassword().equals(userVo.getRepassword())) {
            model.addAttribute("error", "两次密码输入不一致");
            return "toRegisterUser";
        }
        try {
            userService.saveUserInfo(userVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "viewUser";
    }

    @GetMapping(value = "toLogin")
    public String toLogin() {
        return "toLogin";
    }

    @PostMapping(value = "login")
    public String login(UserRegisterForm userVo, Model model) {
        if (StringUtils.isEmpty(userVo.getUsername())) {
            model.addAttribute("error", "username null error");
            return "toLogin";
        }
        if (StringUtils.isEmpty(userVo.getPassword())) {
            model.addAttribute("error", "密码不能为空");
            return "toLogin";
        }
        try {
            boolean result = userService.verifyUser(userVo);
            if (!result) {
                model.addAttribute("error", "密码不对");
                return "toLogin";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "toLogin";
    }

}
