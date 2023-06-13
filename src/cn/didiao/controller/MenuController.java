package cn.didiao.controller;

import cn.didiao.annotation.Controller;
import cn.didiao.annotation.RequestMapping;
import cn.didiao.annotation.RequesttParameter;
import cn.didiao.annotation.ResposeBody;
import cn.didiao.service.MenuInfoService;
import cn.didiao.service.impl.MenuInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/menu")
public class MenuController {
    MenuInfoService menuInfoService = new MenuInfoServiceImpl();

    @RequestMapping("/showMenu")
    @RequesttParameter
    public String getMenuTree(HttpServletRequest req) {
        menuInfoService.showMenu(req);
        return "/utils/abc";
    }

    @RequestMapping("/getMenu")
    @RequesttParameter
    @ResposeBody
    public String getMenu(HttpServletRequest req) {
        return menuInfoService.getMenuInfoById(req);
    }

    @RequestMapping("/edit")
    @ResposeBody
    @RequesttParameter
    public String editMenu(HttpServletRequest req) {
        return menuInfoService.editMenu(req);

    }

    @RequestMapping("/delete")
    @ResposeBody
    @RequesttParameter
    public String deleteById(HttpServletRequest req) {
        return menuInfoService.statusById(req, 0);
    }

    @RequestMapping("/enable")
    @ResposeBody
    @RequesttParameter
    public String enableById(HttpServletRequest req) {
        return menuInfoService.statusById(req, 1);
    }

    @RequestMapping("/addMenu")
    @ResposeBody
    @RequesttParameter
    public String addMenu(HttpServletRequest req) {
        return menuInfoService.addMenu(req);
    }

    @RequestMapping("/limit")
    @ResposeBody
    @RequesttParameter
    public String limit(HttpServletRequest req){
        return   menuInfoService.showMenuAll(req);
    }
}
