package com.nhnacademy.hello.login;

import com.nhnacademy.hello.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(Objects.nonNull(session)){
            session.invalidate();
        }

        Cookie cookie = CookieUtils.getCookie(req, "JSESSIONID");
        if(Objects.nonNull(cookie)){
            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        resp.sendRedirect("/login.html");

    }
}
