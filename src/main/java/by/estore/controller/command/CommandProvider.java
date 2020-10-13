package by.estore.controller.command;

import by.estore.controller.command.impl.*;
import by.estore.controller.command.impl.page.*;

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
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
