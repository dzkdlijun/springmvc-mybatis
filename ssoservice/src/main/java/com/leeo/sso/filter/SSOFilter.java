package com.leeo.sso.filter;

import com.leeo.sso.controller.SSOController;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lijun on 2016/12/16.
 */
public class SSOFilter extends OncePerRequestFilter {
    /**
     * 需要排除的页面
     */
    private String excludedPages;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = "";
        Cookie[] cookies = request.getCookies();
        String url = request.getRequestURL().toString();
        if(url.endsWith(excludedPages)){
            filterChain.doFilter(request,response);
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SSO")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        String service = request.getParameter("service");
        if (username != null && !username.equals("")) {
            long time = System.currentTimeMillis();
            SSOController.tickets.put("" + time, username);
            if (service.indexOf("?") >= 0) {
                service = service + "&ticket=" + time;
            } else {
                service = service + "?ticket=" + time;
            }
            response.sendRedirect(service);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void setExcludedPages(String excludedPages) {
        this.excludedPages = excludedPages;
    }
}
