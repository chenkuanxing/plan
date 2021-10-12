package com.xinghui.ctrl.mgt;

import com.xinghui.config.UnAccessTokenAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HtmlCtrl {

    @RequestMapping("/login")
    @UnAccessTokenAuth
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    @UnAccessTokenAuth
    public String index() {
        return "index";
    }

    @RequestMapping("/planList")
    @UnAccessTokenAuth
    public String planList() {
        return "planList";
    }

    @RequestMapping("/user")
    @UnAccessTokenAuth
    public String user() {
        return "user";
    }

    @RequestMapping("/role")
    @UnAccessTokenAuth
    public String role() {
        return "role";
    }

}
