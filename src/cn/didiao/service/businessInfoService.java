package cn.didiao.service;

import javax.servlet.http.HttpServletRequest;

public interface businessInfoService {
    void getBusinessInfoAll(HttpServletRequest req);

    String getbusinessById(HttpServletRequest req);

    String editOrAddBusiness(HttpServletRequest req);
}
