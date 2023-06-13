package cn.didiao.dao.impl;

import cn.didiao.component.BgmsConfig;
import cn.didiao.dao.userInfoDao;
import cn.didiao.entity.UserInfo;
import cn.didiao.utils.BaseDao;
import cn.didiao.utils.SqlOperate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class userInfoDaoImpl extends BaseDao<UserInfo> implements userInfoDao {


    @Override
    public UserInfo getUserInfo(String username, String password) {
        return selectOne("select tb2.*,tb1.name from user_info tb2 left join role tb1 on tb2.role_id = tb1.id where user_name=? and password=?" , UserInfo.class,username,password);
    }

    @Override
    public boolean update(Map<String, String[]> parameterMap, String tb,Integer id) {
        String sql= splicingSql(parameterMap,tb);
        Object[] parameters=getParams(parameterMap);
        return executeUpdate(sql,parameters,id)>0;
    }

    @Override
    public List<Map<String, Object>> getUserInfoBylimit(int i, Integer rows, String keyword) {

        String sql="select u1.*,r1.name from user_info u1 left join role r1 on u1.role_id=r1.id where user_name like concat('%',?,'%') limit ?,?";
        List<Map<String, Object>> maps = selectForListMap(sql, keyword, i, rows);
        sql="select count(1) count from user_info where user_name like concat('%',?,'%');";
        maps.add(selectForMap(sql, keyword));

        return maps;
    }

    @Override
    public UserInfo getUserById(int id) {

        return selectOne("select r1.*,r2.name from "+BgmsConfig.USER_TABLE_NAME+" r1 left join role r2  on r1.role_id = r2.id where r1.id=?;", UserInfo.class,id);
    }

    @Override
    public int newUpdate(Map<String, String> stringStringMap, String id) {
        String sql = SqlOperate.sqlPJ(stringStringMap,BgmsConfig.USER_TABLE_NAME);
        String[] ids=new String[1];
        if (id.contains(",")){
             ids = id.split(",");
        }else {
            ids[0]=id;
        }
        sql+=" where id in(";
        for (int i = 0; i <ids.length ; i++) {
            if (i==ids.length-1){
                sql+="?)";
            }else {
                sql+="?,";
            }
        }
        String[] parameter = SqlOperate.getParameter(stringStringMap);
        return executeUpdate(sql,parameter,ids);
    }

    @Override
    public int addUser(Map<String, String> stringStringMap) {
        String sql = SqlOperate.insertSql(stringStringMap, BgmsConfig.USER_TABLE_NAME);
        String[] parameter = SqlOperate.getParameter(stringStringMap);
        return executeUpdate(sql,parameter);
    }


    private Object[] getParams(Map<String, String[]> parameterMap) {
        Object[] arr=new Object[parameterMap.size()];
        int index=0;
        for (String s : parameterMap.keySet()) {
            for (String s1 : parameterMap.get(s)) {

                arr[index]=s1;
            }

            index++;
        }
        return arr;
    }

    private String splicingSql(Map<String, String[]> parameterMap, String tb) {
        StringBuilder sql=new StringBuilder("update "+tb+" set ");

        Set<String> keys = parameterMap.keySet();
        for (String key : keys) {
            sql.append(key+"=? ,");
        }
        String substring = sql.substring(0, sql.length() - 1);
        substring+="where id=?";
        return substring;

    }
}
