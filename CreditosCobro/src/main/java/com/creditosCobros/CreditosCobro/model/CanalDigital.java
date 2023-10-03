package com.creditosCobros.CreditosCobro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanalDigital implements Serializable {
    private Integer canalDigitalId;
    private String descripcion;
    private String estado;
}
