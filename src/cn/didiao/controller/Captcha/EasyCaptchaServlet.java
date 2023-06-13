package cn.didiao.controller.Captcha;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/captcha")
public class EasyCaptchaServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // png类型
//        SpecCaptcha captcha = new SpecCaptcha(130, 48);
//        captcha.text();  // 获取验证码的字符
//        captcha.textChar();  // 获取验证码的字符数组
//
//        // gif类型
//        GifCaptcha captcha = new GifCaptcha(130, 48);
//
//        // 中文类型
//        ChineseCaptcha captcha = new ChineseCaptcha(130, 48);
//
//        // 中文gif类型
//        ChineseGifCaptcha captcha = new ChineseGifCaptcha(130, 48);

        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(2);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        String text = captcha.text();// 获取运算的结果：5
        System.out.println(text);
        req.getSession().setAttribute("code",text);
        captcha.out(resp.getOutputStream());  // 输出验证码


    }
}
