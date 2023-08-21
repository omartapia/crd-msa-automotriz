package com.pichincha.crd.automotriz.util;

import com.pichincha.crd.automotriz.exceptions.ConflictException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public static void handlingValidationError(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        String message = constants.FIELDS_ERROR + fieldError.getField() + "': " + fieldError.getDefaultMessage();
        throw new ConflictException(message);
    }
}
