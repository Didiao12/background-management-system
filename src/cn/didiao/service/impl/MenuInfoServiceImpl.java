package cn.didiao.service.impl;

import cn.didiao.component.BgmsConfig;
import cn.didiao.dao.MenuInfoDao;
import cn.didiao.dao.impl.MenuInfoDaoImpl;
import cn.didiao.entity.Limit;
import cn.didiao.entity.MenuInfo;
import cn.didiao.entity.UserInfo;
import cn.didiao.entity.Vo.MenuInfoVo;
import cn.didiao.service.MenuInfoService;
import cn.didiao.utils.BaseResult;
import cn.didiao.utils.SqlOperate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuInfoServiceImpl implements MenuInfoService {
    private MenuInfoDao menuInfoDao = new MenuInfoDaoImpl();

    @Override
    public void showMenu(HttpServletRequest req) {
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);
        List<MenuInfoVo> menuInfoVos = assemblyMenuTree(userInfo.getId());

        req.getSession().setAttribute("MenuList", menuInfoVos);
    }

    @Override
    public String showMenuAll(HttpServletRequest req) {
        Integer page;
        Integer rows;
        String keyword = "";
        try {
            page = Integer.parseInt(req.getParameter("pageIndex"));
            rows = Integer.parseInt(req.getParameter("rows"));
            keyword = req.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
        } catch (NumberFormatException e) {
            page = 1;
            rows = 5;
        }
        int i = Integer.parseInt(menuInfoDao.getCount());
        //查询根菜单
        List<MenuInfo> menuGen = menuInfoDao.getMenuInfoListByPid(((UserInfo) req.getSession().getAttribute("user")).getId(), -1);

        req.getSession().setAttribute("menuGen", menuGen);
        List<Map<String, Object>> menuAll = null;
        //查询所有菜单
        menuAll = menuInfoDao.getMenuAll((page - 1) * rows, rows, keyword);
        req.getSession().setAttribute("menuInfo", menuAll);


        int count = Integer.parseInt(menuAll.get(menuAll.size() - 1).get("count").toString());
        return BaseResult.success(new Limit(i, count % rows == 0 ? count / rows : count / rows + 1, menuAll));
    }

    @Override
    public String getMenuInfoById(HttpServletRequest req) {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            MenuInfo menuInfo = menuInfoDao.getMenuById(id);
            if (menuInfo != null) {
                return BaseResult.success(menuInfo);
            } else {
                return BaseResult.error(302, "未知错误！");
            }
        } catch (NumberFormatException e) {
            return BaseResult.error(301, "参数异常！");

        }
    }

    @Override
    public String editMenu(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String> newMap = SqlOperate.MapZH(parameterMap);
        String idStr = newMap.remove("id");
        if (newMap.size() <= 0) {
            return BaseResult.error(302, "参数异常！！");
        }
        try {
            Integer id = Integer.parseInt(idStr);
            boolean rows = menuInfoDao.updateMenu(newMap, id);
            if (rows) {
                return BaseResult.success();
            }
            return BaseResult.error(303, "未知错误！");
        } catch (NumberFormatException e) {
            return BaseResult.error(302, "参数异常！！");
        }

    }

    @Override
    public String statusById(HttpServletRequest req, Integer status) {
        String idStr = req.getParameter("id");
        String[] ids;
        if (idStr != null && idStr != "") {
            if (idStr.contains(",")) {
                ids = idStr.split(",");
            } else {
                ids = new String[1];
                ids[0] = idStr;
            }

            boolean b = menuInfoDao.deleteById(ids, status);
            if (b) {
                return BaseResult.success();
            } else {
                return BaseResult.error(308, "未知错误");
            }
        }

        return BaseResult.error(307, "参数异常");
    }

    @Override
    public String addMenu(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String> stringStringMap = SqlOperate.MapZH(parameterMap);
        boolean b = menuInfoDao.insertMenu(stringStringMap, req);
        if (b) {
            return BaseResult.success();
        }
        return BaseResult.error(309, "未知错误哦！！！");
    }




    private List<MenuInfoVo> assemblyMenuTree(Integer uid) {
        List<MenuInfo> oneMenuList = menuInfoDao.getMenuInfoListByPid(uid, -1);
        List<MenuInfoVo> arr = new ArrayList<>();
        oneMenuList.forEach(menuInfo ->
                {
                    MenuInfoVo menuInfoVo = menuInfoConvertMenuInfoVo(menuInfo);
                    menuInfoVo.setList(menuInfoDao.getMenuInfoListByPid(uid, menuInfoVo.getId()));
                    arr.add(menuInfoVo);
                }
        );
        return arr;
    }

    private MenuInfoVo menuInfoConvertMenuInfoVo(MenuInfo menuInfo) {
        MenuInfoVo menuInfoVo = new MenuInfoVo();
        menuInfoVo.setId(menuInfo.getId());
        menuInfoVo.setTitle(menuInfo.getTitle());
        menuInfoVo.setIcon(menuInfo.getIcon());
        menuInfoVo.setHref(menuInfo.getHref());
        menuInfoVo.setPId(menuInfo.getPId());
        menuInfoVo.setCreatetime(menuInfo.getCreatetime());
        menuInfoVo.setStatus(menuInfo.getStatus());

        return menuInfoVo;

    }
}
