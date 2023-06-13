package cn.didiao.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getRequestURI();
        System.out.println(contextPath);
        if(contextPath.contains("index.jsp")||"/bgms/".equals(contextPath)){
            if (request.getSession().getAttribute("user")!=null){
                response.sendRedirect(request.getContextPath()+"/utils/abc.jsp");
            }else {
                filterChain.doFilter(request,response);
            }

        }else {
            if (contextPath.contains("login")||contextPath.contains("static")||contextPath.contains("captcha")
                    ||contextPath.contains("/bgms/js/")||contextPath.contains("images")){
                filterChain.doFilter(request,response);
            }else {
                if (request.getSession().getAttribute("user")!=null){
                    filterChain.doFilter(request,response);
                }else {
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }
            }
        }


    }
}
