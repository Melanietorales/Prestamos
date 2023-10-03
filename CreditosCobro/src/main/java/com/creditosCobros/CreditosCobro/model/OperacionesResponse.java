package com.creditosCobros.CreditosCobro.model;

import com.creditosCobros.CreditosCobro.dto.PrestamoCabeceraDTO;
import com.creditosCobros.CreditosCobro.dto.PrestamoOperacionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperacionesResponse {
    @JsonProperty(value = "CAB")
private PrestamoCabeceraDTO cabeceraDTO;
@JsonProperty(value = "OPE")
private List<PrestamoOperacionDTO> operaciones;
@JsonIgnore
private String nombre;
@JsonIgnore
    private String apellido;
@JsonIgnore
    private Long cuenta;
}
