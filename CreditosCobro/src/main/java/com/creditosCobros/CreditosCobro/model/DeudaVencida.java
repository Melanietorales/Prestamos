package com.creditosCobros.CreditosCobro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeudaVencida {
    private String nroCuenta;
    private int nroCuota;
    private Date fechaVencimiento;
    private BigDecimal montoTotalCobrar;
}
