package com.creditosCobros.CreditosCobro.exception;

public class APIException extends Exception{
    private APIExceptionType type;
    private String code;
    private Boolean useApiMessage;
    public APIException(APIExceptionType type, String code, String message, Boolean useApiMessage, Throwable cause) {
        super(message, cause);
        this.setType(type);
        this.setCode(code);
        this.setUseApiMessage(useApiMessage);
    }

    public APIException(APIExceptionType type, String code, String message, Throwable cause) {
        this(type, code, message, false, cause);
    }

    public APIException(APIClientException clientEx) {
        this(clientEx.getErrorBody().getType(), clientEx.getErrorBody().getCode(), clientEx.getErrorBody().getMessage(), clientEx.getErrorBody().getUseApiMessage());
    }

    public APIException(APIExceptionType type, String code, String message) {
        this(type, code, message, false, (Throwable)null);
    }

    public APIException(APIExceptionType type, String code, String message, Boolean useApiMessage) {
        this(type, code, message, useApiMessage, (Throwable)null);
    }

    public APIException(APIExceptionType type, String code) {
        this(type, code, (String)null, false, (Throwable)null);
    }

    public APIException(APIExceptionType type, String code, Boolean useApiMessage) {
        this(type, code, (String)null, useApiMessage, (Throwable)null);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public APIExceptionType getType() {
        return this.type;
    }

    public void setType(APIExceptionType type) {
        this.type = type;
    }

    public Boolean getUseApiMessage() {
        return this.useApiMessage;
    }

    public void setUseApiMessage(Boolean useApiMessage) {
        this.useApiMessage = useApiMessage;
    }
}
