package org.leceido.todosimple.exceptions;



import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;

    public ErrorResponse(int status, String message, String stackTrace, List<ValidationError> errors) {
        this.status = status;
        this.message = message;
        this.stackTrace = stackTrace;
        this.errors = errors;
    }

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String toJson() {
        return "{\"status\": " + getStatus() + ", " + "\"message\": \"" + getMessage() + "\"}";
    }


    private static class ValidationError {
        private final String field;
        private final String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }


    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public void addValidationError(String field, String message) {
        if(Objects.isNull(errors)) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(new ValidationError(field, message));
    }
}
