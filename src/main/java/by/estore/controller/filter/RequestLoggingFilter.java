package by.estore.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestLoggingFilter extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        logAllInfo(req, res);
        chain.doFilter(req, res);
    }

    private void logAllInfo(HttpServletRequest req, HttpServletResponse res) {

        String params = Collections.list(req.getParameterNames()).stream()
                .map(param -> "\t" + param + ": " + Arrays.toString(req.getParameterValues(param)))
                .collect(Collectors.joining("\n"));
        logger.debug("params\n{}", params);

        String headers = Collections.list(req.getHeaderNames()).stream()
                .map(header -> "\t" + header + ": " + req.getHeader(header))
                .collect(Collectors.joining("\n"));
        logger.debug("headers\n{}", headers);

        if (req.getSession() != null) {
            String session = Collections.list(req.getSession().getAttributeNames()).stream()
                    .map(attribute -> "\t" + attribute + ": " + req.getSession().getAttribute(attribute))
                    .collect(Collectors.joining("\n"));
            logger.debug("session\n{}", session);
        } else {
            logger.debug("session\n{}", "null");
        }

        if (req.getCookies() != null) {
            String cookies = Stream.of(req.getCookies())
                    .map(cookie -> "\t" + cookie.getName() + ": " + cookie.getValue())
                    .collect(Collectors.joining("\n"));
            logger.debug("cookies\n{}", cookies);
        } else {
            logger.debug("cookies\n{}", "null");
        }
    }
}
