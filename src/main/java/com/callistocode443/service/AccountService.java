package com.callistocode443.service;

import com.callistocode443.config.AccountProperties;
import com.callistocode443.exception.AccountNotFoundException;
import com.callistocode443.exception.CannotCloseLastAccountException;
import com.callistocode443.exception.NotEnoughMoneyException;
import com.callistocode443.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final List<Account> accountList;
    private final AccountProperties accountProperties;
    private int accountIdCount = 1;

    public AccountService(AccountProperties accountProperties) {
        this.accountList = new ArrayList<>();
        this.accountProperties = accountProperties;
    }

    public Account createAccount(int userId) {
        Account newAccount = new Account(accountIdCount++, userId, accountProperties.getDefaultAmount());
        accountList.add(newAccount);
        return newAccount;
    }

    public void deposit(int accountId, double amount) {
        Account account = findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("Аккаунт не найден: " + accountId));
        account.setMoneyAmount(account.getMoneyAmount() + amount);
    }

    public void withdraw(int accountId, double amount) {
        Account account = findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("Аккаунт не найден: " + accountId));
        if (account.getMoneyAmount() < amount) {
            throw new NotEnoughMoneyException(accountId, account.getMoneyAmount(), amount);
        }
        account.setMoneyAmount(account.getMoneyAmount() - amount);
    }

    public void transfer(int accountIdSender, int accountIdReceiver, double amount) {
        Account accountSender = findAccountById(accountIdSender).orElseThrow(() -> new AccountNotFoundException("Аккаунт отправителя не найден: " + accountIdSender));
        Account accountReceiver = findAccountById(accountIdReceiver).orElseThrow(() -> new AccountNotFoundException("Аккаунт получателя не найден: " + accountIdReceiver));

        boolean isSameHuman = accountSender.getUserId() == accountReceiver.getUserId();
        withdraw(accountIdSender, amount);
        double amountToTransfer = isSameHuman ? amount : amount * (1 - accountProperties.getTransferCommission());
        deposit(accountIdReceiver, amountToTransfer);
    }

    public void closeAccount(int accountId) {
        Account account = findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("Аккаунт не найден: " + accountId));

        Optional<Account> anotherAccount = accountList.stream()
                .filter(ac -> ac.getUserId() == account.getUserId() && !ac.equals(account))
                .findFirst();

        if (anotherAccount.isPresent()) {
            Account foundAccount = anotherAccount.get();
            foundAccount.setMoneyAmount(foundAccount.getMoneyAmount() + account.getMoneyAmount());
            accountList.remove(account);
        } else {
            throw new CannotCloseLastAccountException("Вы не можете закрыть свой последний счёт");
        }
    }

    public Optional<Account> findAccountById(int accountId) {
        return accountList.stream().filter(account -> account.getId() == accountId).findFirst();
    }

    public List<Account> getAccountsByUserId(int userId) {
        return accountList.stream()
                .filter(account -> account.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
