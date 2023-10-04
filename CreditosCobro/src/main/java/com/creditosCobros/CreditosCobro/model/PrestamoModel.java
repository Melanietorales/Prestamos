package com.creditosCobros.CreditosCobro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PrestamoModel {
    private Long numeroPrestamo;

    private Integer modulo;

    private Integer sucursal;

    private Integer moneda;

    private String monedaLetras;

    private Integer papel;

    private Long operacion;

    private Integer subCuenta;

    private Integer tipoOperacion;

    private String fechaVencimiento;

    private BigDecimal monto;

    private List<CuotaModel> cuotas;

    private Integer cantidadCuotas;

    private Date fechaVencimientoCuota;
}
