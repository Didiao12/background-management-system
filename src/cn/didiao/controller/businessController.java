package cn.didiao.controller;

import cn.didiao.annotation.Controller;
import cn.didiao.annotation.RequestMapping;
import cn.didiao.annotation.RequesttParameter;
import cn.didiao.annotation.ResposeBody;
import cn.didiao.service.businessInfoService;
import cn.didiao.service.impl.businessInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/business")
public class businessController {

    private businessInfoService businessService=new businessInfoServiceImpl();
    @RequestMapping("/getbusiness")
    @ResposeBody
    @RequesttParameter
    public String getbusiness(HttpServletRequest req){
        return businessService.getbusinessById(req);
    }

    @RequestMapping("/edit")
    @ResposeBody
    @RequesttParameter
    public String businessEdit(HttpServletRequest req){
        return businessService.editOrAddBusiness(req);
    }


}
