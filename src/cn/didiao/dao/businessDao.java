package cn.didiao.dao;

import cn.didiao.entity.BusinessInfo;

import java.util.List;
import java.util.Map;

public interface businessDao {
    List<Map<String, Object>> getbusinessInfoBylimit(int i, Integer rows, String keyword);

    BusinessInfo getbusinessInfoById(Integer id);

    int editBusiness(String sql, String[] parameter, String id);

}
