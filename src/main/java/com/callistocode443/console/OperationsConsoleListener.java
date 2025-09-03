package com.callistocode443.console;

import com.callistocode443.command.ConsoleOperationType;
import com.callistocode443.command.OperationCommand;
import com.callistocode443.command.user.CreateUserCommand;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OperationsConsoleListener {
    private final Map<ConsoleOperationType, OperationCommand> commandMap;
    private final Scanner scanner;

    public OperationsConsoleListener(List<OperationCommand> commands, Scanner scanner) {
        commandMap = new HashMap<>();
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));
        this.scanner = scanner;
    }

    public void operationListener() {
        while (true) {
            System.out.println("Пожалуйста введите тип операции");
            Arrays.stream(ConsoleOperationType.values()).forEach(System.out::println);
            System.out.println('\n');

            String operationStr = scanner.nextLine();
            try {
                ConsoleOperationType operation = ConsoleOperationType.valueOf(operationStr);
                OperationCommand command = commandMap.get(operation);
                if (command != null) {
                    command.execute();
                } else {
                    System.out.println("Команда не найдена");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверная команда");
            }
        }
    }
}
