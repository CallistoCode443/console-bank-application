package com.callistocode443.command.account;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.exception.AccountNotFoundException;
import com.callistocode443.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TransferAccountCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public TransferAccountCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введите ID счёта отправителя:");
            int senderAccountId = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите ID счёта получателя:");
            int receiverAccountId = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите сумму перевода:");
            double amount = Double.parseDouble(scanner.nextLine());
            try {
                accountService.transfer(senderAccountId, receiverAccountId, amount);
            } catch (AccountNotFoundException e) {
                System.out.println("Аккаунт не найден: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный аргумент: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
