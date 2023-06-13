package cn.didiao.dao;

import cn.didiao.entity.MenuInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MenuInfoDao {

    List<MenuInfo> getMenuInfoListByPid(Integer uid,Integer pid);


    List<Map<String, Object>> getMenuAll(Integer start,Integer rows,String keyword);

    String getCount();

    MenuInfo getMenuById(Integer id);

    boolean updateMenu(Map<String, String> newMap, Integer id);

    boolean deleteById(String[] ids,Integer status);

    boolean insertMenu(Map<String, String> stringStringMap, HttpServletRequest req);
}
