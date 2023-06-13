package cn.didiao.dao.impl;

import cn.didiao.component.BgmsConfig;
import cn.didiao.dao.MenuInfoDao;
import cn.didiao.entity.MenuInfo;
import cn.didiao.entity.UserInfo;
import cn.didiao.utils.BaseDao;
import cn.didiao.utils.SqlOperate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MenuInfoDaoImpl extends BaseDao<MenuInfo> implements MenuInfoDao {

    @Override
    public List<MenuInfo> getMenuInfoListByPid(Integer uid,Integer pid) {
//        String sql="select * from t_menu_info where status=1 and pid=?";
        String sql="select mi.*\n" +
                "from menu_info mi\n" +
                "left join t_role_menu trm on mi.id=trm.menu_id\n" +
                "left join user_info ui on ui.role_id=trm.role_id\n" +
                "where ui.id=? and mi.p_id=? and mi.status=1";
        return selectForList(sql, MenuInfo.class,uid,pid);
    }

    @Override
    public List<Map<String, Object>> getMenuAll(Integer start, Integer rows,String keyword ) {

        String sql="select m2.title AS name,m1.* from menu_info m1 left join menu_info m2 on m1.p_id=m2.id where m1.title like concat('%',?,'%') limit ?,?";
        List<Map<String, Object>> maps = selectForListMap(sql, keyword, start, rows);
        String sql1="select count(1) count from menu_info m1 left join menu_info m2 on m1.p_id=m2.id where m1.title like concat('%',?,'%')";
        List<Map<String, Object>> maps1 = selectForListMap(sql1, keyword);
        maps.add(maps1.get(0));
        return  maps;


    }

    @Override
    public String getCount() {
        return  selectForMap("select count(1) count from "+BgmsConfig.MENU_TABLE_NAME).get("count").toString();
    }

    @Override
    public MenuInfo getMenuById(Integer id) {
        return selectOne("select * from "+BgmsConfig.MENU_TABLE_NAME+" where id=?",MenuInfo.class,id);
    }

    @Override
    public boolean updateMenu(Map<String, String> newMap, Integer id) {

        StringBuilder sql= new StringBuilder(SqlOperate.sqlPJ(newMap,BgmsConfig.MENU_TABLE_NAME));
            sql.append("where id=?");
            String[] a= SqlOperate.getParameter(newMap);
            return executeUpdate(sql.toString(),a,id)>0;
    }

    @Override
    public boolean deleteById(String[] ids,Integer status) {
        StringBuilder sb=new StringBuilder("update "+BgmsConfig.MENU_TABLE_NAME+" set status=? where ");
        for (String id : ids) {
            sb.append("id=? or ");
        }
        String sql = sb.substring(0, sb.length() - 3);
        return executeUpdate(sql,status,ids)>0;
    }

    @Override
    public boolean insertMenu(Map<String, String> stringStringMap ,HttpServletRequest req) {
        String sql = SqlOperate.insertSql(stringStringMap,BgmsConfig.MENU_TABLE_NAME);
        String[] parameter = SqlOperate.getParameter(stringStringMap);
        if (executeUpdate(sql,parameter)>0) {
            return  executeUpdate("insert into t_role_menu values(null,?,?)",
                    ((UserInfo)req.getSession().getAttribute("user")).getId(),
                    selectForMap("select max(id) max from "+BgmsConfig.MENU_TABLE_NAME).get("max"))>0;
        }
        return false;
    }


}
