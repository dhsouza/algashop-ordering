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

    public static void requiresValidEmail(String email, String errorMessagge) {
        if (email.isBlank()) {
            throw new IllegalArgumentException(errorMessagge == null ? VALIDATION_ERROR_EMAIL_IS_BLANK : errorMessagge);
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException(errorMessagge == null ? VALIDATION_ERROR_EMAIL_IS_INVALID : errorMessagge);
        }
    }
}
