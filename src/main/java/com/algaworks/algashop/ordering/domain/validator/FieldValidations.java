package com.algaworks.algashop.ordering.domain.validator;

import org.apache.commons.validator.routines.EmailValidator;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_BLANK;
import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.VALIDATION_ERROR_EMAIL_IS_INVALID;

public class FieldValidations {

    private FieldValidations() {
        throw new IllegalStateException("Utility class");
    }

    public static void requiresValidEmail(String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(String email, String errorMessage) {
        if (email.isBlank()) {
            throw new IllegalArgumentException(errorMessage == null ? VALIDATION_ERROR_EMAIL_IS_BLANK : errorMessage);
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException(errorMessage == null ? VALIDATION_ERROR_EMAIL_IS_INVALID : errorMessage);
        }
    }
}
