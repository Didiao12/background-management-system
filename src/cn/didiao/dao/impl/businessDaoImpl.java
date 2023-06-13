package cn.didiao.dao.impl;

import cn.didiao.component.BgmsConfig;
import cn.didiao.dao.businessDao;
import cn.didiao.entity.BusinessInfo;
import cn.didiao.utils.BaseDao;

import java.util.List;
import java.util.Map;

public class businessDaoImpl extends BaseDao<BusinessInfo> implements businessDao {
    @Override
    public List<Map<String, Object>> getbusinessInfoBylimit(int i, Integer rows, String keyword) {
        String sql = "select * from "+ BgmsConfig.BUSINESS_INFO +" where businessName like concat('%',?,'%') limit ?,?";
        List<Map<String, Object>> maps = selectForListMap(sql, keyword, i, rows);
        maps.add(selectForMap("select count(1) count from "+BgmsConfig.BUSINESS_INFO+" where businessName like concat('%',?,'%')", keyword));
        return maps;
    }

    @Override
    public BusinessInfo getbusinessInfoById(Integer id) {
        return selectOne("select * from "+BgmsConfig.BUSINESS_INFO+" where id=?",BusinessInfo.class,id);
    }

    @Override
    public int editBusiness(String sql, String[] parameter, String id) {
        String[] ids=new String[1];
        if (id==null){
            return executeUpdate(sql,parameter);
        }else {
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
            return executeUpdate(sql,parameter,ids);
        }

    }
}
