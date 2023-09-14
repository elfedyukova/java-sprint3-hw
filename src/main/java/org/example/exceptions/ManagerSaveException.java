package org.example.exceptions;

public class ManagerSaveException extends RuntimeException {
    public ManagerSaveException(String errorMsg) {
        super(errorMsg);
    }
}