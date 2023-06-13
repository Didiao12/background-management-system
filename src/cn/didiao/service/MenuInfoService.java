package cn.didiao.service;

import javax.servlet.http.HttpServletRequest;

public interface MenuInfoService {

    void showMenu(HttpServletRequest req);

    String showMenuAll(HttpServletRequest req);

    String getMenuInfoById(HttpServletRequest req);

    String editMenu(HttpServletRequest req);

    String statusById(HttpServletRequest req ,Integer status);

    String addMenu(HttpServletRequest req);
}
