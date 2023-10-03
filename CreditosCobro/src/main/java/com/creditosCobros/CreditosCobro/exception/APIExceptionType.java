package com.creditosCobros.CreditosCobro.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum APIExceptionType {
    INTERNAL,
    SECURITY,
    COMMUNICATION,
    DATABASE,
    APPLICATION;
}
