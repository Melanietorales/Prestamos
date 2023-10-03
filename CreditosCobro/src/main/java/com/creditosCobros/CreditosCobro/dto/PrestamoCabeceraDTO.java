package com.creditosCobros.CreditosCobro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoCabeceraDTO {
@JsonProperty(value = "SIS")
    private Integer codigoProducto;
@JsonProperty(value = "NOM")
    private String nombreProducto;
@JsonProperty(value = "COP")
    private Integer cantidadOperacionesVigentes;

}
