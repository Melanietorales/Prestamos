package com.creditosCobros.CreditosCobro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaCliente {
    @JsonProperty(value = "PAIS")
    public Integer codigoPais;

    @JsonProperty(value = "TDOC")
    public Integer codigoTipo;

    @JsonProperty(value = "DOCUMENTO")
    public String nroDocumento;

    public String tipoOperacion;

    private Date fechaNacimiento;

    private String telefono;
}
