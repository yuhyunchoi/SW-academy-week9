package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


@Slf4j
public class DomainCookieServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(DomainCookieServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String requestUri = req.getRequestURI();
        log.error("requestUri:{}" + requestUri);
        if(requestUri.endsWith("/read")){
            readCookie(req, resp);
        }else if(requestUri.endsWith("/write")){
            writeCookie(req, resp);
        }else{
            show(req, resp);
        }
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try(PrintWriter out = resp.getWriter()){
            out.println("<html>");
            out.println("<head><title>cookie test</title></head>");
            out.println("<body>");
            out.println("<ul>");
            out.println("<li><a href='http://a.com:8080/domain-cookie/write?domain=a.com'>set cookie: domain=a.com, path=/</a></li>");
            out.println("<li><a href='http://a.com:8080/domain-cookie/write?domain=.a.com'>set cookie: domain=.a.com, path=/</a></li>");
            out.println("<li><a href='http://1.a.com:8080/domain-cookie/write?domain=.1.a.com'>set cookie: domain=.1.a.com, path=/</a></li>");
            out.println("<li><a href='http://1.a.com:8080/domain-cookie/more/write?domain=.a.com'>set cookie: domain=.a.com, path=/domain-cookie/more</a></li>");

            out.println("<li><a href='http://a.com:8080/domain-cookie/read'>get cookie: domain=a.com</a><br /></li>");
            out.println("<li><a href='http://b.com:8080/domain-cookie/read'>get cookie: domain=b.com</a><br /></li>");
            out.println("<li><a href='http://1.a.com:8080/domain-cookie/read'>get cookie: domain=1.a.com</a><br /></li>");
            out.println("<li><a href='http://2.a.com:8080/domain-cookie/read'>get cookie: domain=2.a.com</a><br /></li>");
            out.println("<li><a href='http://1.a.com:8080/domain-cookie/more/read'>get cookie: domain=1.a.com, path=/domain-cookie/more/read</a><br /></li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private static final String COOKIE_NAME = "cook2";

    private void readCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Objects.isNull(req.getCookies())){
            return;
        }
        try(PrintWriter out = resp.getWriter()){
            for(int i = 0; i < req.getCookies().length; i++){
                Cookie cookie = req.getCookies()[i];
                out.println(cookie.getName() + ":" + cookie.getValue());
            }
        }catch(Exception e){
            log.error("read-cookie: "+ e.getMessage());
        }
    }

    private static final String MORE_PATH = "/domain-cookie/more/write";

    private void writeCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String domain = req.getParameter("domain");
        String path = MORE_PATH.equals(req.getRequestURI()) ? MORE_PATH.replace("/write", "") : "/";

        Cookie newCookie = new Cookie(COOKIE_NAME, req.getRequestURL().append("?").append(req.getQueryString()).toString());
        newCookie.setDomain(domain);
        newCookie.setPath(path);

        log.error("cookieName: " + newCookie.getName());
        log.error("cookieValue: " + newCookie.getValue());
        log.error("cookieDomain: " + newCookie.getDomain());

        show(req, resp);
    }
}
