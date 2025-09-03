package com.callistocode443.command.account;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.command.user.CreateUserCommand;
import com.callistocode443.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateAccountCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public CreateAccountCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введите ID пользователя");
            int userId = Integer.parseInt(scanner.nextLine());
            try {
                accountService.createAccount(userId);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный аргумент: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}
