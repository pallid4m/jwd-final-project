package by.estore.controller.filter;

import by.estore.controller.command.CommandName;
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

    private static final String CSRF_TOKEN = "csrf_token";

    private static final String COMMAND_PARAM = "command";

    private static final String POST_METHOD = "POST";

    // TODO: 14-Oct-20 generate a token
    private static final String CSRF_TOKEN_SAMPLE = "csrf_sample";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (session.getAttribute(CSRF_TOKEN) == null) {
            session.setAttribute(CSRF_TOKEN, CSRF_TOKEN_SAMPLE);
        }

        if (req.getMethod().equalsIgnoreCase(POST_METHOD)) {
            if (!Objects.equals(req.getParameter(CSRF_TOKEN), session.getAttribute(CSRF_TOKEN))) {
                session.invalidate();
                res.sendRedirect(req.getContextPath() + RouteHolder.SIGN_IN_PAGE);
                return;
            }
        }

        String command = req.getParameter(COMMAND_PARAM);
        if (command.equals(CommandName.CART_PAGE) || command.equals(CommandName.USER_PAGE) || command.equals(CommandName.ADMIN_PAGE)) {
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
