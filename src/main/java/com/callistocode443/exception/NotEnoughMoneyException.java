package com.callistocode443.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException(int accountId, double available, double attempted) {
        super(String.format("Недостаточно средств на счёте ID %d. Доступно: %d, попытка: %d",
                accountId, available, attempted));
    }
}
