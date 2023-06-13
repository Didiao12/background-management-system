package cn.didiao.Servlet;

import cn.didiao.annotation.RequesttParameter;
import cn.didiao.annotation.ResposeBody;
import cn.didiao.component.HandlerMapping;
import cn.didiao.component.InvocationHandler;
import cn.hutool.core.annotation.AnnotationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@WebServlet(value = "/api/*" ,loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {


    private HashMap<String, InvocationHandler> handlerHashMap;
    @Override
    public void init() throws ServletException {
        HandlerMapping handlerMapping = new HandlerMapping();
        handlerHashMap = handlerMapping.urlMapping();
        System.out.println(handlerHashMap);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        if (requestURI.contains(contextPath)){
            requestURI= requestURI.replace(contextPath,"");
        }
        if (requestURI.contains("?")){
            requestURI= requestURI.substring(0,requestURI.indexOf("?"));
        }

        InvocationHandler invocationHandler = handlerHashMap.get(requestURI);

        try {
            Method method = invocationHandler.getMethod();
            Object classObject = invocationHandler.getClassObject();
            String msg ="";
            if (AnnotationUtil.hasAnnotation(method, RequesttParameter.class)){
                 msg = (String) method.invoke(classObject,req);
            }else {
                 msg = (String) method.invoke(classObject);
            }


            if (AnnotationUtil.hasAnnotation(method, ResposeBody.class)) {
                resp.getWriter().write(msg);return;
            }
            if (msg.contains("froward:")){
                req.getRequestDispatcher(msg.replace("froward:","")+".jsp").forward(req,resp);
            }
            if (msg.contains("redirect:")){
                resp.sendRedirect("http://"+req.getServerName()+":"+req.getServerPort()+"/"+contextPath+ msg.replace("redirect:","")+".jsp");
            }

                //默认走转发
            if(!msg.contains("froward:")&&!msg.contains("redirect:")){
                req.getRequestDispatcher(msg+".jsp").forward(req,resp);
            }

        } catch (Exception e) {
            throw new ServletException("没有找到资源");
    }
}

}