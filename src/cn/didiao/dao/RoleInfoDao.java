package cn.didiao.dao;

import cn.didiao.entity.Role;

import javax.management.relation.RoleInfo;
import java.util.List;
import java.util.Map;

public interface RoleInfoDao {

    List<Map<String, Object>> getRoleInfoBylimit(Integer page, Integer rows, String keyword);

    Role getRoleById(int id);

    int updateOrInsertRole(int id, String name);

    int updateRoleStatus(String[] ids,Integer flag);
}
