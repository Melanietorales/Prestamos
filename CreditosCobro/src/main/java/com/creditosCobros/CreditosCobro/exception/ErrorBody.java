package com.creditosCobros.CreditosCobro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorBody {
    private String code;
    private String message;
    private APIExceptionType type;
    private Boolean useApiMessage;
}
