package cn.didiao.service.impl;

import cn.didiao.component.BgmsConfig;
import cn.didiao.dao.RoleInfoDao;
import cn.didiao.dao.impl.RoleInfoDaoImpl;
import cn.didiao.dao.impl.userInfoDaoImpl;
import cn.didiao.dao.userInfoDao;
import cn.didiao.entity.Limit;
import cn.didiao.entity.UserInfo;
import cn.didiao.service.UserService;
import cn.didiao.utils.BaseResult;
import cn.didiao.utils.MD5Util;
import cn.didiao.utils.RequestParameterUtil;
import cn.didiao.utils.SqlOperate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    userInfoDao user = new userInfoDaoImpl();

    @Override
    public String getUserInfoByUsername(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String jsonMsg = "";
        req.getSession().removeAttribute("code");
        UserInfo userinfo = user.getUserInfo(username, MD5Util.encryptMD5(password, username));
        if (userinfo != null) {
            req.getSession().setAttribute(BgmsConfig.SESSION_USER_KEY, userinfo);
            jsonMsg = BaseResult.success();
        } else {
            jsonMsg = BaseResult.error(202, "登录失败！账户或密码错误！");
        }


        return jsonMsg;
    }






    @Override
    public String Update(HttpServletRequest req, String tb) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String[]> parameterMas = new HashMap<>();
        UserInfo userInfo= (UserInfo)(req.getSession().getAttribute("user"));
        boolean flag=false;
        boolean flag1=false;
        for (String s : parameterMap.keySet()) {
            flag=false;
            String[] strings = parameterMap.get(s);
            if (strings!=null){
                for (String string : strings) {
                    if (string==null||string==""||string.contains(" ")){
                        flag=true;
                        break;
                    }
                }
                if (flag){
                    continue;
                }
                if ("password".equals(s)){
                    flag1=true;
                    String pwd = parameterMap.get(s)[0];
                    if (pwd==""||pwd==null||pwd.contains(" ")) {
                        return BaseResult.error(207,"信息不能为空||不能包含空格！！");
                    }

                    String[] en=new String[1];
                    en[0]=MD5Util.encryptMD5(pwd,userInfo.getUserName());
                    parameterMas.put(s,en);
                }else {
                    parameterMas.put(s,strings) ;
                }
            }else {
                return BaseResult.error(207,"信息不能为空！");
            }
        }
        parameterMas.remove("oldPwd");
        if (parameterMas.size()==0){
            return BaseResult.error(207,"信息不能为空！||不能包含空格！");
        }
        boolean b = user.update(parameterMas, tb,((UserInfo)(req.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY))).getId());
        if (b) {
            if (flag1){
                req.getSession().removeAttribute("user");
            }
            updateNC(userInfo,parameterMas);
            return BaseResult.success();
        }
        return BaseResult.error(206,"修改失败！");
    }

    @Override
    public String getUserInfoAll(HttpServletRequest req) {
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


        List<Map<String, Object>> userAll= user.getUserInfoBylimit((page-1)*rows,rows,keyword);
        int count = Integer.parseInt(userAll.remove(userAll.size() - 1).get("count").toString());
        Limit limit = new Limit(0, count % rows == 0 ? count / rows : count / rows + 1, userAll);
        req.setAttribute(BgmsConfig.User_ALL,limit);
        req.setAttribute(BgmsConfig.KEYWORD,keyword);
        req.setAttribute(BgmsConfig.PAGE_INDEX,page);
        RoleInfoDao roleInfoDao=new RoleInfoDaoImpl();
        List<Map<String, Object>> roleInfoBylimit = roleInfoDao.getRoleInfoBylimit(0, 999, "");
        roleInfoBylimit.remove(roleInfoBylimit.size()-1);
        System.out.println(roleInfoBylimit);
        req.setAttribute(BgmsConfig.ROLEALL,roleInfoBylimit);
        return "/utils/UserGL";
    }

    @Override
    public String getUserInfo(HttpServletRequest req) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
           UserInfo userInfo= user.getUserById(id);
           if (user!=null){
               return BaseResult.success(userInfo);
           }
           return BaseResult.error(303,"获取失败");

        } catch (NumberFormatException e) {
           return BaseResult.error(302,"参数异常");
        }
    }

    @Override
    public String editUser(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String> stringStringMap = SqlOperate.MapZH(parameterMap);
        String id = stringStringMap.remove("id");
        String password = stringStringMap.remove("password");

        if (password!=null){
            stringStringMap.put("password",MD5Util.encryptMD5(password,stringStringMap.get("user_name")));
        }
        //证明是修改
        int rows;


        if (id!=null){
            stringStringMap.remove("user_name");
            rows=user.newUpdate(stringStringMap,id);
        }else {
            rows=user.addUser(stringStringMap);
        }
        if (rows>0){
          return   BaseResult.success();
        }
        return BaseResult.error(303,"保存失败");

    }


    private void updateNC(UserInfo user, Map<String, String[]> parameterMas) {
        for (String s : parameterMas.keySet()) {
            try {
              String methodName=(s.charAt(0)+"").toUpperCase()+s.substring(1);
                Method method = user.getClass().getMethod("set" + methodName,user.getClass().getDeclaredField(s).getType());
//                Class<?> returnType = method.getReturnType();
                method.invoke(user, parameterMas.get(s)[0]);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }


    }


}

