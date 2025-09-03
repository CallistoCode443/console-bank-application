package com.callistocode443.command.account;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.exception.AccountNotFoundException;
import com.callistocode443.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class WithdrawAccountCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public WithdrawAccountCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введите ID счёта: ");
            int accountId = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите сумму: ");
            double amount =Double.parseDouble(scanner.nextLine());
            try {
                accountService.withdraw(accountId, amount);
            } catch (AccountNotFoundException e) {
                System.out.println("Аккаунт не найден: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный аргумент: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}
