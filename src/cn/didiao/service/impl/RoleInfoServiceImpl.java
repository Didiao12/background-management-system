package cn.didiao.service.impl;

import cn.didiao.dao.RoleInfoDao;
import cn.didiao.dao.impl.RoleInfoDaoImpl;
import cn.didiao.entity.Limit;
import cn.didiao.entity.Role;
import cn.didiao.service.RoleInfoService;
import cn.didiao.utils.BaseResult;
import cn.didiao.utils.RequestParameterUtil;
import com.mysql.cj.util.StringUtils;

import javax.management.BadAttributeValueExpException;
import javax.management.relation.RoleInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleInfoServiceImpl implements RoleInfoService {
    RoleInfoDao roleInfoDao=new RoleInfoDaoImpl();
    @Override
    public String showMenu(HttpServletRequest req) {
        ArrayList arrayList = RequestParameterUtil.parameterDispose(req);
        Integer page=1;
        Integer rows=5;
        String keyword="";
        int i=0;
        for (Object o : arrayList) {
                try {
                    if (i%3==0){
                        page=Integer.parseInt(o.toString());
                        i++;
                    }else if(i%3==1){
                        rows=Integer.parseInt(o.toString());
                        i++;
                    }else if(i%3==2){
                        rows=Integer.parseInt(o.toString());
                        i++;
                    }
                } catch (NumberFormatException e) {
                    keyword=o.toString();
                }
        }

        List<Map<String,Object>> roleAll= roleInfoDao.getRoleInfoBylimit((page-1)*rows,rows,keyword);

        int count = Integer.parseInt(roleAll.remove(roleAll.size() - 1).get("count").toString());
        Limit limit = new Limit(0, count % rows == 0 ? count / rows : count / rows + 1, roleAll);
        req.setAttribute("roleList",limit);
            req.setAttribute("keyword",keyword);
            req.setAttribute("page",page);
        return BaseResult.success(limit);
    }

    @Override
    public String getRoleById(HttpServletRequest req) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Role roleInfo= roleInfoDao.getRoleById(id);
            if (roleInfo!=null){
               return BaseResult.success(roleInfo);
            }
            return BaseResult.error(303,"查询异常");
        } catch (NumberFormatException e) {
            return BaseResult.error(302,"参数异常");
        }
    }

    @Override
    public String roleEditOrSave(HttpServletRequest req) {
        int id ;
        try {
           id=  Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id=-1;
        }
        String name = req.getParameter("name");
        int rows= roleInfoDao.updateOrInsertRole(id,name);
        if (rows>0){
            return BaseResult.success();
        }
        return BaseResult.error(303,"保存异常");
    }

    @Override
    public String updateRoleStatus(HttpServletRequest req, Integer flag) {
        String id = req.getParameter("id");
        String[] ids=new String[1];
        if (!StringUtils.isNullOrEmpty(id)){
            if (id.contains(",")){
                 ids = id.split(",");
            }else {
                ids[0]=id;
            }
            int rows= roleInfoDao.updateRoleStatus(ids,flag);
            if(rows>0){
                return BaseResult.success();
            }
            return BaseResult.error(303, "修改失败！");
        }
        return BaseResult.error(302,"参数异常");
    }
}
