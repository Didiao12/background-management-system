package cn.didiao.dao.impl;

import cn.didiao.dao.RoleInfoDao;
import cn.didiao.entity.MenuInfo;
import cn.didiao.entity.Role;
import cn.didiao.utils.BaseDao;

import javax.management.relation.RoleInfo;
import java.util.List;
import java.util.Map;

public class RoleInfoDaoImpl extends BaseDao<Role> implements RoleInfoDao {


    @Override
    public List<Map<String, Object>> getRoleInfoBylimit(Integer start, Integer rows, String keyword) {
        String sql = "select * from role where name like concat('%',?,'%') limit ?,?";
        List<Map<String, Object>> maps = selectForListMap(sql, keyword, start, rows);
        maps.add(selectForMap("select count(1) count from role where name like concat('%',?,'%')", keyword));
        return maps;
    }

    @Override
    public Role getRoleById(int id) {
        return selectOne("select * from role where id=?", Role.class, id);
    }

    @Override
    public int updateOrInsertRole(int id, String name) {
        if (id == -1) {
            String sql="insert into role values(null,?,?)";
            return executeUpdate(sql,name,1);
        }else {
            String sql="update role set name=? where id=?";
            return executeUpdate(sql,name,id);
        }
    }

    @Override
    public int updateRoleStatus(String[] ids,Integer flag) {
        StringBuilder sql=new StringBuilder("update role set status=? where id in(");
        for (int i = 0; i < ids.length; i++) {
            if (i== ids.length-1){
                sql.append("?)");
            }else {
                sql.append("?,");
            }
        }
        return executeUpdate(sql.toString(),flag,ids);
    }
}














