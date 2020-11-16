package by.estore.web.controller.command.impl.admin;

import by.estore.entity.OrderStatus;
import by.estore.service.AdminService;
import by.estore.service.ServiceFactory;
import by.estore.service.exception.ServiceException;
import by.estore.web.controller.command.Command;
import by.estore.web.controller.command.RouteHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateOrderCommand.class);

    private final AdminService adminService = ServiceFactory.getInstance().getAdminService();

    private static final String ORDER_ID_PARAM = "id";
    private static final String ORDER_STATE_PARAM = "state";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter(ORDER_STATE_PARAM);
        Long id = Long.parseLong(request.getParameter(ORDER_ID_PARAM));

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setState(state);

        try {
            adminService.updateOrderStateById(id, orderStatus);
        } catch (ServiceException e) {
            logger.error(e);
        }

        response.sendRedirect(request.getContextPath() + RouteHolder.ADMIN_PAGE);
    }
}
