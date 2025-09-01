package com.callistocode443.service;

import com.callistocode443.exception.UserAlreadyExistsException;
import com.callistocode443.model.Account;
import com.callistocode443.model.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> userList;
    private final AccountService accountService;
    private int userIdCount = 1;

    public UserService(AccountService accountService) {
        this.userList = new ArrayList<>();
        this.accountService = accountService;
    }

    public User createUser(String login) {
        boolean isLoginBusy = userList.stream().anyMatch(user -> login != null && login.equals(user.getLogin()));

        if (isLoginBusy) {
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует");
        }

        User newUser = new User(userIdCount++, login);
        accountService.createAccount(newUser.getId());
        userList.add(newUser);
        return newUser;
    }

    public Optional<User> findUserById(int userId) {
        return userList.stream().filter(user -> user.getId() == userId).findFirst();
    }

    public List<User> getAllUser() {
        return userList;
    }
}
