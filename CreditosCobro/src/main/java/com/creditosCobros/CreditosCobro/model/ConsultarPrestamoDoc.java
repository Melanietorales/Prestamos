package com.creditosCobros.CreditosCobro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarPrestamoDoc {
    @JsonProperty(value = "Pais")
    private Integer codigoPais;

    @JsonProperty(value = "TDoc")
    private Integer codigoTipoDoc;

    @JsonProperty(value = "NroDoc")
    private String numeroDocumento;

}
