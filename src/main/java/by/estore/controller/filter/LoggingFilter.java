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

public class LoggingFilter extends HttpFilter {
    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        logAllInfo(request, response);
        chain.doFilter(request, response);
    }

    private void logAllInfo(HttpServletRequest request, HttpServletResponse response) {

        String params = Collections.list(request.getParameterNames()).stream()
                .map(param -> "\t" + param + ": " + Arrays.toString(request.getParameterValues(param)))
                .collect(Collectors.joining("\n"));
        logger.debug("params\n{}", params);

        String headers = Collections.list(request.getHeaderNames()).stream()
                .map(header -> "\t" + header + ": " + request.getHeader(header))
                .collect(Collectors.joining("\n"));
        logger.debug("headers\n{}", headers);

        if (request.getSession(false) != null) {
            String session = Collections.list(request.getSession().getAttributeNames()).stream()
                    .map(attribute -> "\t" + attribute + ": " + request.getSession().getAttribute(attribute))
                    .collect(Collectors.joining("\n"));
            logger.debug("session\n{}", session);
        } else {
            logger.debug("session\n{}", "null");
        }

        if (request.getCookies() != null) {
            String cookies = Stream.of(request.getCookies())
                    .map(cookie -> "\t" + cookie.getName() + ": " + cookie.getValue())
                    .collect(Collectors.joining("\n"));
            logger.debug("cookies\n{}", cookies);
        } else {
            logger.debug("cookies\n{}", "null");
        }
    }
}
