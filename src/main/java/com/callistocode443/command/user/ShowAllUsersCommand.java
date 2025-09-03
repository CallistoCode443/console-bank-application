package com.callistocode443.command.user;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.model.Account;
import com.callistocode443.model.User;
import com.callistocode443.service.AccountService;
import com.callistocode443.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllUsersCommand implements OperationCommand {
    private final UserService userService;
    private final AccountService accountService;

    public ShowAllUsersCommand(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        List<User> userList = userService.getAllUser();

        if (userList.isEmpty()) {
            System.out.println("Пользователей не найдено");
            return;
        }

        System.out.println("Список всех пользователей:");
        for(User user : userList) {
            System.out.println(user);
            System.out.println("Счета: " + accountService.getAccountsByUserId(user.getId()));
            System.out.println();
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}
