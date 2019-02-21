package com.sams.filter;

import com.sams.constant.Constants;
import com.sams.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AccessFilter implements Filter {

    @Value("${static-project-url}")
    private String url;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("访问控制过滤器开始");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if(user != null){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(url+"view/login.jsp");
        }
    }

    @Override
    public void destroy() {
        log.info("访问控制过滤器结束");
    }
}
