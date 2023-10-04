package com.creditosCobros.CreditosCobro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamosPersona {
    public static final String NOCTA = "NOCTA";

    private String nombres;
private String apellidos;
private Long numeroCuenta;
private List<PrestamoModel> prestamo;
}
