package com.realMoney.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.realMoney.entity.user.User;
import com.realMoney.service.user.LoginService;
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
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("")
    public Response<User> userLogin(User user){
        switch (user.getLoginType()){
            case 1 : return loginService.userAndPasswordLogin(user);
            case 2 : return loginService.emailCodeLogin(user);
            case 3 : return loginService.thridPartyLogin(user);

        }
        return ResponseUtil.buildErrorResponse(ResponseCode.USER_PASSWORD_NULL);
    }


    @RequestMapping("/getCode")
    public void getCode(User user, HttpServletResponse response){
        if(StringUtil.isNotBlank(user.getUserName())){
            Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", -1);
            response.setContentType("image/jpeg");
            ServletOutputStream sos = null;
            try {

                sos = response.getOutputStream();
                ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(null != sos){
                    try {
                        sos.flush();
                        sos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            PrintWriter writer = null;
            try {
                response.setContentType("application/json;charset=UTF-8");
                writer = response.getWriter();
                String s = JSONObject.toJSONString(ResponseUtil.buildErrorResponse(ResponseCode.USER_PASSWORD_NULL));
                writer.write(s);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(writer != null ){
                        writer.flush();
                        writer.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
