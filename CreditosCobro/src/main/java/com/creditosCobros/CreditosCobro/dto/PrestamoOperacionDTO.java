package com.creditosCobros.CreditosCobro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoOperacionDTO {
    @JsonProperty(value = "NRO")
    private Long numeroOperacion;

    @JsonProperty(value = "SBO")
    private Integer subOperacion;

    @JsonProperty(value = "TOP")
    private Integer tipoOperacion;

    @JsonProperty(value = "MDA")
    private Integer moneda;

    @JsonProperty(value = "MON")
    private String monedaLetras;

    @JsonProperty(value = "MOD")
    private Integer modulo;

    @JsonProperty(value = "PAP")
    private Integer papel;

    @JsonProperty(value = "SUC")
    private Integer sucursal;

    @JsonProperty(value = "FVA")
    private Date fechaDesembolso;

    @JsonProperty(value = "FVT")
    private Date fechaVencimiento;

    @JsonProperty(value = "PZO")
    private Integer plazoDias;

    @JsonProperty(value = "TAS")
    private BigDecimal tasa;

    @JsonProperty(value = "TTA")
    private Integer tipoTasa;

    @JsonProperty(value = "tipoTasaDescripcion")
    private String ttd;

    @JsonProperty(value = "AON")
    private BigDecimal interesAlVencimiento;

    @JsonProperty(value = "SIG")
    private String signoMoneda;

    @JsonProperty(value = "SDO")
    private BigDecimal saldo;

    @JsonProperty(value = "EBC")
    private String comunal;

    @JsonIgnore
    private DetallePrestamoDTO detalle;
}
