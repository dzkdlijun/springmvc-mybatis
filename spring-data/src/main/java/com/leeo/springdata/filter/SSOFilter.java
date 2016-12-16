package com.leeo.springdata.filter;

import com.leeo.springdata.controller.HomeController;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by lijun on 2016/12/16.
 */
public class SSOFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String url = URLEncoder.encode(request.getRequestURL().toString());
        if(username==null){
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("SSO")){
                        username = cookie.getValue();
                        break;
                    }
                }
            }
            if(username!=null && !username.equals("")){
                session.setAttribute("username",username);
                filterChain.doFilter(request,response);
            }
            else{
                String ticket = request.getParameter("ticket");
                if(ticket!=null&&!ticket.equals("")){
                    PostMethod postMethod = new PostMethod(HomeController.SSO_URL+"/ticket");
                    NameValuePair pair = new NameValuePair("ticket",ticket);
                    postMethod.addParameter(pair);
                    HttpClient client = new HttpClient();
                    client.executeMethod(postMethod);
                    username = postMethod.getResponseBodyAsString();
                    postMethod.releaseConnection();
                    if(username!=null&&!username.equals("")){
                        session.setAttribute("username",username);
                        filterChain.doFilter(request,response);
                    }else {
                        response.sendRedirect(HomeController.SSO_URL+"?service="+url);
                    }
                }else {
                    response.sendRedirect(HomeController.SSO_URL+"?service="+url);
                }
                return;
            }
        }else {
            filterChain.doFilter(request,response);
        }

    }

}
