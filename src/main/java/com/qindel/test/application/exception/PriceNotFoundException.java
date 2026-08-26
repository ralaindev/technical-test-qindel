package com.qindel.test.application.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException() {
        super("No se ha encontrado un precio aplicable.");
    }
}