package com.realMoney.controller;

import com.realMoney.entitiy.Login;
import com.realMoney.utils.CodeUtil;
import com.realMoney.utils.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {



    @RequestMapping("/getCode")
    public void getCode(Login login, HttpServletResponse response){
        if(StringUtil.isNotBlank(login.getUsername())){
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

        }

    }
}
