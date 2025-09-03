package com.callistocode443.command;

public interface OperationCommand {
    void execute();
    ConsoleOperationType getOperationType();
}
