package cn.didiao.controller;

import cn.didiao.annotation.Controller;
import cn.didiao.annotation.RequestMapping;
import cn.didiao.annotation.RequesttParameter;
import cn.didiao.annotation.ResposeBody;
import cn.didiao.service.MenuInfoService;
import cn.didiao.service.RoleInfoService;
import cn.didiao.service.UserService;
import cn.didiao.service.businessInfoService;
import cn.didiao.service.impl.MenuInfoServiceImpl;
import cn.didiao.service.impl.RoleInfoServiceImpl;
import cn.didiao.service.impl.UserServiceImpl;
import cn.didiao.service.impl.businessInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/pages")
public class pagesController {
    MenuInfoService menuInfoService = new MenuInfoServiceImpl();
    RoleInfoService roleInfoService=new RoleInfoServiceImpl();
    UserService userService=new UserServiceImpl();
    businessInfoService businessService=new businessInfoServiceImpl();

    @RequestMapping("/tap")
    @RequesttParameter
    public String tapPages(HttpServletRequest req) {
        String flag = req.getParameter("flag");
        if (flag != null) {
            if ("MyInfo".equals(flag)) {
                return "/utils/MyInfo";
            } else if ("updatePwd".equals(flag)) {
                return "/utils/updatePwd";
            } else if ("Home".equals(flag)) {
                return "/utils/Home";
            } else if (flag.contains("menuMG")) {
                return "/utils/menuMG";
            }else if (flag.contains("edit")) {
                req.setAttribute("index",req.getParameter("index"));
                return "/utils/edit";
            }else if("roleMenu".equals(flag)){
                roleInfoService.showMenu(req);
                return "/utils/roleMenu";
            }else if("UserGL".equals(flag)){
                userService.getUserInfoAll(req);
                return "/utils/UserGL";
            } else if ("businessGL".equals(flag)) {
                businessService.getBusinessInfoAll(req);
                return "/utils/businessGL";
            }else if("chatGPT".equals(flag)){
                return "/utils/chatGPT";
            }
        }
        return "/utils/Home";
    }
}
