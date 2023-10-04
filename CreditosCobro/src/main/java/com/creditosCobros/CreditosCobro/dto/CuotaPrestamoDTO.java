package com.creditosCobros.CreditosCobro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuotaPrestamoDTO {
    @JsonProperty(value = "Prev")
    private CuotaPrestamoPrevDTO prev;
}
