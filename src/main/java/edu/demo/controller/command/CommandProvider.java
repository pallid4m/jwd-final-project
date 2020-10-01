package edu.demo.controller.command;

import edu.demo.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.LOGIN_PAGE, new LoginPageCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.LOCALE, new LocaleCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.GET_REGISTRATION_PAGE, new RegistrationPageCommand());
    }

    public Command getCommand(String command) {
        String commandNameUpper = command.toUpperCase();
        CommandName commandName = CommandName.valueOf(commandNameUpper);
        return commands.get(commandName);
    }
}
