package com.creditosCobros.CreditosCobro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosCliente {
    @JsonProperty(value = "PNOMBRE")
    private String primerNombre;

    @JsonProperty(value = "SNOMBRE")
    private String segundoNombre;

    @JsonProperty(value = "PAPELLIDO")
    private String primerApellido;

    @JsonProperty(value = "SAPELLIDO")
    private String segundoApellido;

    @JsonProperty(value = "SEXO")
    private String sexo;

    @JsonProperty(value = "PAISNACIMIENTO")
    private Integer paisNacimiento;

    @JsonProperty(value = "NACIONALIDAD")
    private Integer paisNacionalidad;

    @JsonProperty(value = "FNACIMIENTO")
    private Date fechaNacimiento;

    @JsonProperty(value = "FVENCDOC")
    private Date fechaVencimiento;

    @JsonProperty(value = "TELEFONO")
    private String telefono;

    @JsonProperty(value = "CELULAR")
    private String nroCelular;

    @JsonProperty(value = "EMAIL")
    private String email;

    @JsonProperty(value = "DIRECCION")
    private String direccion;

    @JsonProperty(value = "PROFESION")
    private Integer codProfesion;

    @JsonProperty(value = "OCUPACION")
    private Integer codOcupacion;

    private String codPersona;

    private String descProfesion;

    private String descProfesionIng;

    private int codError;

    private String mensaje;
}
