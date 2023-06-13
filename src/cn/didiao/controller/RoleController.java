package cn.didiao.controller;

import cn.didiao.annotation.Controller;
import cn.didiao.annotation.RequestMapping;
import cn.didiao.annotation.RequesttParameter;
import cn.didiao.annotation.ResposeBody;
import cn.didiao.service.RoleInfoService;
import cn.didiao.service.impl.RoleInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/role")
public class RoleController {
    private RoleInfoService roleInfoService=new RoleInfoServiceImpl();

    @RequestMapping("/limit")
    @ResposeBody
    @RequesttParameter
    public String limit(HttpServletRequest req){
       return   roleInfoService.showMenu(req);
    }

    @RequestMapping("/getRole")
    @ResposeBody
    @RequesttParameter
    public String getRole(HttpServletRequest req){
       return roleInfoService.getRoleById(req);
    }

    @RequestMapping("/edit")
    @ResposeBody
    @RequesttParameter
    public String roleEdit(HttpServletRequest req){
        return roleInfoService.roleEditOrSave(req);
    }

    @RequestMapping("/save")
    @ResposeBody
    @RequesttParameter
    public String saveRole(HttpServletRequest req){
        return roleEdit(req);
    }
    @RequestMapping("/delete")
    @ResposeBody
    @RequesttParameter
    public String deleteRole(HttpServletRequest req) {
        return roleInfoService.updateRoleStatus(req,0);
    }
    @RequestMapping("/enable")
    @ResposeBody
    @RequesttParameter
    public String enableRole(HttpServletRequest req){
            return roleInfoService.updateRoleStatus(req,1);
    }


    @RequestMapping("/inquire")
    @ResposeBody
    @RequesttParameter
    public String inquire(HttpServletRequest req){
        return roleInfoService.showMenu(req);

    }
}
