package cn.didiao.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class RequestParameterUtil {


    public static ArrayList parameterDispose(HttpServletRequest req){
        ArrayList arr=new ArrayList();
        Integer page;
        Integer rows;
        String keyword = "";
        try {
            page = Integer.parseInt(req.getParameter("pageIndex"));
            rows = Integer.parseInt(req.getParameter("rows"));
            keyword = req.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
        } catch (NumberFormatException e) {
            page = 1;
            rows = 5;
            keyword = req.getParameter("keyword");
            if(keyword == null) {keyword="";}
        }
        arr.add(page);
        arr.add(rows);
        arr.add(keyword);
     return arr;

    }
}
