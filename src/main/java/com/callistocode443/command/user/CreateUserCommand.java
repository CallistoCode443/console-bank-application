package com.callistocode443.command.user;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.exception.UserAlreadyExistsException;
import com.callistocode443.model.User;
import com.callistocode443.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateUserCommand implements OperationCommand {
    private final UserService userService;
    private final Scanner scanner;

    public CreateUserCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введите login пользователя:");
            String login = scanner.nextLine();
            try {
                User user = userService.createUser(login);
                System.out.println("Пользователь создан: " + user);
            } catch (UserAlreadyExistsException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный аргумент: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
