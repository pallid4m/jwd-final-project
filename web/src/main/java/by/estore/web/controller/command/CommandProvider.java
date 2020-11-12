package by.estore.web.controller.command;

import by.estore.web.controller.command.impl.admin.OrderListCommand;
import by.estore.web.controller.command.impl.admin.ProductListCommand;
import by.estore.web.controller.command.impl.admin.UserListCommand;
import by.estore.web.controller.command.impl.page.*;
import by.estore.web.controller.command.impl.SignInCommand;
import by.estore.web.controller.command.impl.SignOutCommand;
import by.estore.web.controller.command.impl.SignUpCommand;
import by.estore.web.controller.command.impl.user.cart.AddProductToCartCommand;
import by.estore.web.controller.command.impl.user.cart.ChangeProductCountCommand;
import by.estore.web.controller.command.impl.user.cart.PlaceOrderCommand;
import by.estore.web.controller.command.impl.user.cart.RemoveProductFromCartCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.MAIN_PAGE, new MainPageCommand());
        commands.put(CommandName.ADMIN_PAGE, new AdminPageCommand());
        commands.put(CommandName.USER_PAGE, new UserPageCommand());
        commands.put(CommandName.CATALOG_PAGE, new CatalogPageCommand());
        commands.put(CommandName.PRODUCT_PAGE, new ProductPageCommand());
        commands.put(CommandName.CART_PAGE, new CartPageCommand());
        commands.put(CommandName.SIGN_IN_PAGE, new SignInPageCommand());
        commands.put(CommandName.SIGN_UP_PAGE, new SignUpPageCommand());

        commands.put(CommandName.SIGN_IN, new SignInCommand());
        commands.put(CommandName.SIGN_UP, new SignUpCommand());
        commands.put(CommandName.SIGN_OUT, new SignOutCommand());

        commands.put(CommandName.USER_LIST, new UserListCommand());
        commands.put(CommandName.PRODUCT_LIST, new ProductListCommand());
        commands.put(CommandName.ORDER_LIST, new OrderListCommand());

        commands.put(CommandName.ADD_PRODUCT_TO_CART, new AddProductToCartCommand());
        commands.put(CommandName.REMOVE_PRODUCT_FROM_CART, new RemoveProductFromCartCommand());
        commands.put(CommandName.CHANGE_PRODUCT_COUNT, new ChangeProductCountCommand());
        commands.put(CommandName.PLACE_ORDER, new PlaceOrderCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
