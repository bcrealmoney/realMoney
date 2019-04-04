package com.realMoney.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        rep.setHeader("Access-Control-Allow-Origin","*");
        rep.setHeader("Access-Control-Allow-Credentials", "true");
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS");
        rep.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        rep.setCharacterEncoding("utf-8");
        chain.doFilter(req, rep);
    }

    @Override
    public void destroy() {

    }
}
