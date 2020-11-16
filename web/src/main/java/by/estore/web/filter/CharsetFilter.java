package by.estore.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final String INIT_ENCODING_PARAM = "requestEncoding";

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter(INIT_ENCODING_PARAM);
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }

        next.doFilter(request, response);
    }
}
