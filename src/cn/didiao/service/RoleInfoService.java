package cn.didiao.service;

import javax.servlet.http.HttpServletRequest;

public interface RoleInfoService {
    String showMenu(HttpServletRequest req);

    String getRoleById(HttpServletRequest req);

    String roleEditOrSave(HttpServletRequest req);

    String updateRoleStatus(HttpServletRequest req,Integer flag);
}
