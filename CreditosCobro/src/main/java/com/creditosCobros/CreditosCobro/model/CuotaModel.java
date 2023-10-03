package com.creditosCobros.CreditosCobro.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
public class CuotaModel {
    private Integer numeroDocumento;
    private String fechaVencimiento;
    private BigDecimal montoPagar;
}
