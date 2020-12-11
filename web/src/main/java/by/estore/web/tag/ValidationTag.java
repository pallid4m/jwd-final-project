package by.estore.web.tag;

import by.estore.web.validation.Error;
import by.estore.web.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

public class ValidationTag extends SimpleTagSupport {
    public static final String ERRORS_ATTRIBUTE = "errors";

    private String path;

    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    @Override
    public void doTag() throws JspException, IOException {

        Writer out = getWriter();

        try {
            PageContext pageContext = (PageContext) getJspContext();
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

            Object object = request.getAttribute(ERRORS_ATTRIBUTE);
            Errors errors = new Errors();
            if (object != null) {
                errors = (Errors) object;
            }
            for (Error error : errors.getErrors()) {
                if (error.getType().equals(path)) {
                    String message = error.getMessage();
                    out.write("<p style=\"color:red\">" + message + "</p>");
                }
            }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Validation tag", ex);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
