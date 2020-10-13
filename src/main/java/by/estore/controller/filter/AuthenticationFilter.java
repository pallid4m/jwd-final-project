package by.estore.controller.filter;

import by.estore.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/main")
public class AuthenticationFilter extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    private String CSRF_TOKEN = "sample token";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            session = req.getSession();
            session.setAttribute("csrf_token", CSRF_TOKEN);
        }

        if (req.getMethod().equalsIgnoreCase("POST")) {
            String token = req.getParameter("csrf_token");
            if (!Objects.equals(token, CSRF_TOKEN)) {
                session.invalidate();
                res.sendRedirect(req.getContextPath() + RouteHolder.SIGN_IN_PAGE);
                return;
            }
        }

        String command = req.getParameter("command");
        if (command.equals("cart-page") || command.equals("user-page") || command.equals("admin-page")) {
            if (session.getAttribute("user") == null) {
                res.sendRedirect(req.getContextPath() + RouteHolder.SIGN_IN_PAGE);
                return;
            }
        }

        super.doFilter(req, res, chain);
    }

    private Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();

        for (Cookie value : cookies) {
            if (value.getName().equals(name)) {
                return value;
            }
        }

        return null;
    }
}
