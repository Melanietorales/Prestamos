package com.creditosCobros.CreditosCobro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CuotaModel {
    private Integer numeroCuota;
    private String fechaVencimiento;
    private BigDecimal montoPagar;
}
