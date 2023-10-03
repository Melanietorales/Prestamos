package com.creditosCobros.CreditosCobro.exception;

public class APIClientException {
    private ErrorBody errorBody;

    public APIClientException(ErrorBody errorBody) {
        this.errorBody = errorBody;
    }

    public APIClientException(String code, String message, APIExceptionType type) {
        this(code, message, type, false);
    }

    public APIClientException(String code, String message, APIExceptionType type, Boolean useApiMessage) {
        this.errorBody = new ErrorBody();
        this.errorBody.setCode(code);
        this.errorBody.setMessage(message);
        this.errorBody.setType(type);
        this.errorBody.setUseApiMessage(useApiMessage);
    }

    public APIClientException(APIExceptionType type, String code, String message) {
        this(code, message, type, false);
    }

    public ErrorBody getErrorBody() {
        return this.errorBody;
    }
}
