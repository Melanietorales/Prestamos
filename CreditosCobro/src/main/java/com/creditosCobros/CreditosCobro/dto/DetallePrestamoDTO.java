package com.creditosCobros.CreditosCobro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class DetallePrestamoDTO {
@JsonProperty(value = "FSit")
private Date fsip;
@JsonProperty(value = "CCuo")
private Integer cantidadCuotas;
@JsonProperty(value = "Cuo")
    List<CuotaPrestamoDTO> cuotasPrestamo;
}
