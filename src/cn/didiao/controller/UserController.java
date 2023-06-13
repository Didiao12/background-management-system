package cn.didiao.controller;

import cn.didiao.annotation.Controller;
import cn.didiao.annotation.RequestMapping;
import cn.didiao.annotation.RequesttParameter;
import cn.didiao.annotation.ResposeBody;
import cn.didiao.component.BgmsConfig;
import cn.didiao.entity.UserInfo;
import cn.didiao.service.UserService;
import cn.didiao.service.impl.UserServiceImpl;
import cn.didiao.utils.BaseResult;
import cn.didiao.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private  UserService userService=new UserServiceImpl();

    @RequestMapping("/login")
    @RequesttParameter
    @ResposeBody
    public String getUserInfo(HttpServletRequest req){
        String code = req.getParameter("code");
        String code1 = (String) req.getSession().getAttribute("code");
        String jsonMsg="" ;
        if (code1!=null){
            if (code1.equals(code)) {
                jsonMsg=userService.getUserInfoByUsername(req);
            } else {
                jsonMsg = BaseResult.error(201, "验证码错误！");
            }
        }else {
            jsonMsg=BaseResult.error(204,"请先获取验证码");
        }
        return jsonMsg;
    }

    @RequestMapping("/update")
    @ResposeBody
    @RequesttParameter
    public String UpdateUser(HttpServletRequest req){

        return userService.Update(req, BgmsConfig.USER_TABLE_NAME);
    }

    @RequestMapping("/updatePwd")
    @ResposeBody
    @RequesttParameter
    public String UpdatePwd(HttpServletRequest req){
        String oldPwd = req.getParameter("oldPwd");
        UserInfo user = (UserInfo) req.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);

        if (user.getPassword().equals(MD5Util.encryptMD5(oldPwd,user.getUserName()))){
            return userService.Update(req,BgmsConfig.USER_TABLE_NAME);

        }else {
           return BaseResult.error(205,"旧密码不正确！");
        }
    }

    @RequestMapping("/SignOut")
    @RequesttParameter
    public String SignOut(HttpServletRequest req){
        req.getSession().removeAttribute("user");
        return "redirect:/index";
    }
    @RequestMapping("/getUserAll")
    @RequesttParameter
    public String getUserAll(HttpServletRequest req){
        return userService.getUserInfoAll(req);
    }

    @RequestMapping("/getUser")
    @ResposeBody
    @RequesttParameter
    public String getUser(HttpServletRequest req){
        return userService.getUserInfo(req);
    }

    @RequestMapping("/edit")
    @ResposeBody
    @RequesttParameter
    public String editUser(HttpServletRequest req){
        return userService.editUser(req);
    }

}
