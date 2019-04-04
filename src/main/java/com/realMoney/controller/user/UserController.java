package com.realMoney.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.realMoney.entity.user.User;
import com.realMoney.service.user.UserService;
import com.realMoney.utils.CodeUtil;
import com.realMoney.utils.StringUtil;
import com.realMoney.utils.response.Response;
import com.realMoney.utils.response.ResponseCode;
import com.realMoney.utils.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public Response<User> userLogin(User user){
        userService.registUser(user);
        return ResponseUtil.buildErrorResponse(ResponseCode.USER_PASSWORD_NULL);
    }
}
