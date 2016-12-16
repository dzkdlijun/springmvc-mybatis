package com.leeo.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijun on 2016/12/15.
 */
@Controller
public class SSOController {

    public static Map<String,String> tickets = new HashMap<String,String>();

    @RequestMapping(value = {"/","","home","login"})
    public String home(@RequestParam String service, Model model){
        model.addAttribute("service",service);
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public void Login(@RequestParam String username,@RequestParam String password,@RequestParam String service, HttpServletResponse response) throws IOException {
        if(username.equals(password)){
            Cookie cookie = new Cookie("SSO",username);
            cookie.setPath("/");
            response.addCookie(cookie);
            long time = System.currentTimeMillis();
            tickets.put(""+time,username);
            if(service.indexOf("?")>=0){
                service = service+"&ticket="+time;
            }else {
                service = service+"?ticket="+time;
            }
            response.sendRedirect(service);
        }else {
            response.sendRedirect("/login?service="+service);
        }
    }

    @RequestMapping(value = "ticket")
    @ResponseBody
    public String ticket(@RequestParam String ticket) {
        String username = tickets.remove(ticket);
        return username;
    }

}
