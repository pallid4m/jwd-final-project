package by.estore.controller.command;

import by.estore.controller.command.impl.*;
import by.estore.controller.command.impl.page.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("main-page", new MainPageCommand());
        commands.put("admin-page", new AdminPageCommand());
        commands.put("user-page", new UserPageCommand());
        commands.put("catalog-page", new CatalogPageCommand());
        commands.put("product-page", new ProductPageCommand());
        commands.put("sign-in-page", new SignInPageCommand());
        commands.put("sign-up-page", new SignUpPageCommand());

        commands.put("sign-in", new SignInCommand());
        commands.put("sign-up", new SignUpCommand());
        commands.put("sign-out", new SignOutCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
