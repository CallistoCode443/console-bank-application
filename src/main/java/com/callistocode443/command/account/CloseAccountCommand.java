package com.callistocode443.command.account;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.exception.CannotCloseLastAccountException;
import com.callistocode443.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CloseAccountCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public CloseAccountCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введите ID счёта");
            int accountId = Integer.parseInt(scanner.nextLine());
            try {
                accountService.closeAccount(accountId);
            } catch (CannotCloseLastAccountException e) {
                System.out.println("Вы не можете закрыть свой последний счёт" + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ID: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный аргумент: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
