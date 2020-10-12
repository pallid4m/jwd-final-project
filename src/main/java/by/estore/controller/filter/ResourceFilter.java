package by.estore.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"*.jpg", "*.jpeg", "*.png", "*.css", "*.js"})
public class ResourceFilter extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(ResourceFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        logger.debug(req.getRequestURL());
        super.doFilter(req, res, chain);
    }
}
