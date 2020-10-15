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
import java.util.Locale;

@WebFilter("/*")
public class LocalizationFilter extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(LocalizationFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String lang = req.getParameter("lang");
        if (lang != null) {
            logger.debug(req.getQueryString());
            logger.debug(req.getLocale());
//            res.setLocale(Locale.US);
//            res.setLocale(new Locale("ru", "RU"));
            req.getSession().setAttribute("lang", lang);
        }
        super.doFilter(req, res, chain);
    }
}
