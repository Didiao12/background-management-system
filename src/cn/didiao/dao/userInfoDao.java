package cn.didiao.dao;

import cn.didiao.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface userInfoDao {
    UserInfo getUserInfo(String username,String password);


    boolean update(Map<String, String[]> parameterMap, String tb,Integer id);

    List<Map<String, Object>> getUserInfoBylimit(int i, Integer rows, String keyword);

    UserInfo getUserById(int id);

    int newUpdate(Map<String, String> stringStringMap, String id);

    int addUser(Map<String, String> stringStringMap);

}
