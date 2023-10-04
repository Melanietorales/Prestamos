package com.creditosCobros.CreditosCobro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuotaPrestamoDTO {
    @JsonProperty(value = "Nro")
    private Integer numeroCuota;

    @JsonProperty(value = "FPg")
    private Date fechaVencimiento;

    @JsonProperty(value = "Imp")
    private BigDecimal importeAPagar;

    @JsonProperty(value = "Con")
    private Integer con;

    @JsonProperty(value = "Est")
    private Integer est;

    @JsonProperty(value = "Cap")
    private Integer cap;

    @JsonProperty(value = "Ints")
    private Integer ints;

    @JsonProperty(value = "Mor")
    private Integer mor;

    @JsonProperty(value = "Tax")
    private Integer tax;

    @JsonProperty(value = "Seg")
    private Integer seg;

    @JsonProperty(value = "Sta")
    private Integer sta;

}
