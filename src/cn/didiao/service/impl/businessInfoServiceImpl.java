package cn.didiao.service.impl;


import cn.didiao.component.BgmsConfig;
import cn.didiao.dao.businessDao;
import cn.didiao.dao.impl.businessDaoImpl;
import cn.didiao.entity.BusinessInfo;
import cn.didiao.entity.Limit;
import cn.didiao.entity.UserInfo;
import cn.didiao.service.businessInfoService;
import cn.didiao.utils.BaseResult;
import cn.didiao.utils.MD5Util;
import cn.didiao.utils.RequestParameterUtil;
import cn.didiao.utils.SqlOperate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class businessInfoServiceImpl implements businessInfoService {


    private businessDao businessDao=new businessDaoImpl();
    @Override
    public void getBusinessInfoAll(HttpServletRequest req) {
        ArrayList arrayList = RequestParameterUtil.parameterDispose(req);
        Integer page=1;
        Integer rows=5;
        String keyword="";
        int i=0;
        for (Object o : arrayList) {
            try {
                if (i%3==0){
                    page=Integer.parseInt(o.toString());
                }else if(i%3==1){
                    rows=Integer.parseInt(o.toString());
                }else if(i%3==2){
                    keyword=o.toString();
                }
                i++;
            } catch (NumberFormatException e) {
                keyword=o.toString();
            }
        }
        List<Map<String,Object>> roleAll= businessDao.getbusinessInfoBylimit((page-1)*rows,rows,keyword);

        int count = Integer.parseInt(roleAll.remove(roleAll.size() - 1).get("count").toString());
        Limit limit = new Limit(0, count % rows == 0 ? count / rows : count / rows + 1, roleAll);
        req.setAttribute("businessList",limit);
        req.setAttribute("keyword",keyword);
        req.setAttribute("page",page);
    }

    @Override
    public String getbusinessById(HttpServletRequest req) {
        try {
            Integer id= Integer.parseInt(req.getParameter("id"));
            BusinessInfo businessInfo= businessDao.getbusinessInfoById(id);
            if (businessInfo!=null){
                return BaseResult.success(businessInfo);
            }
            return BaseResult.error(302,"查询失败");
        } catch (NumberFormatException e) {
            return BaseResult.error(302,"参数异常");
        }
    }

    @Override
    public String editOrAddBusiness(HttpServletRequest req) {
        Map<String, String> stringStringMap = SqlOperate.MapZH(req.getParameterMap());
        String id = stringStringMap.remove("id");

        String sql;
        String password = stringStringMap.remove("password");
        if (password!=null){
            stringStringMap.put("password", MD5Util.encryptMD5(password,stringStringMap.get("businessName")));
        }
        String[] parameter;
        if (id==null){
            parameter=SqlOperate.getParameter(stringStringMap);
             sql=SqlOperate.insertSql(stringStringMap, BgmsConfig.BUSINESS_INFO);
            //证明是新增

        }else {
            stringStringMap.remove("businessName");
            parameter=SqlOperate.getParameter(stringStringMap);
            sql=SqlOperate.sqlPJ(stringStringMap,BgmsConfig.BUSINESS_INFO);

           // 证明是修改
        }
        int rows= businessDao.editBusiness(sql,parameter,id);

        if (rows>0){
            return BaseResult.success();
        }
        return BaseResult.error(303,"保存失败");
    }
}
