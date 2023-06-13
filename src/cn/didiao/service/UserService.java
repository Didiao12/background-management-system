package cn.didiao.service;

import cn.didiao.entity.UserInfo;
import cn.didiao.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    String getUserInfoByUsername(HttpServletRequest req);


    String Update(HttpServletRequest req,String tb);


    String getUserInfoAll(HttpServletRequest req);

    String getUserInfo(HttpServletRequest req);

    String editUser(HttpServletRequest req);

}
