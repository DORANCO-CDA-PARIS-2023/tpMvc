package com.doranco.coursSpring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SessionDemoController {

    @GetMapping("/session")
    @ResponseBody
    public String session(HttpSession session)
    {
        if (session.getAttribute("login") == null) {
            session.setAttribute("login", "Robert");
            return "Session VIDE";
        }
        return "SESSION OK : " + session.getAttribute("login");
    }

    @GetMapping("/session/remove")
    @ResponseBody
    public String removeSession(HttpSession session)
    {
        session.removeAttribute("login");
        return "Session clean ...";
    }
}