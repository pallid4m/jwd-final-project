package by.estore.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(LocalizationFilter.class);

    private static final Locale EN_US_LOCALE = new Locale("en", "US");
    private static final Locale RU_RU_LOCALE = new Locale("ru", "RU");

    private static final String LANG_PARAM = "lang";

    private static final String EN_US = "en_US";
    private static final String RU_RU = "ru_RU";

    private static final String QUERY_START = "?";
    private static final String QUERY_SEPARATOR = "&";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String lang = request.getParameter(LANG_PARAM);

        if (lang != null) {
            if (lang.equalsIgnoreCase(EN_US)) {
                response.setLocale(EN_US_LOCALE);
            } else {
                response.setLocale(RU_RU_LOCALE);
            }
            request.getSession().setAttribute(LANG_PARAM, lang);
            response.sendRedirect(request.getRequestURI() + QUERY_START + deleteQueryParam(request.getQueryString(), lang));
            return;
        }

        super.doFilter(request, response, chain);
    }

    private String deleteQueryParam(String query, String param) {
        return query.replaceAll(QUERY_SEPARATOR + LANG_PARAM + "=(" + param + ")", "");
    }
}
