package com.creditosCobros.CreditosCobro.mapper;

import com.creditosCobros.CreditosCobro.model.DatosPrestamo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatoPrestamoRowMapper implements RowMapper<DatosPrestamo> {
    @Override
    public DatosPrestamo mapRow(ResultSet resultSet, int i) throws SQLException {
        DatosPrestamo datosPrestamo = new DatosPrestamo();
        datosPrestamo.setNroPrestamo(resultSet.getString("nro_prestamo"));
        datosPrestamo.setMoneda(resultSet.getString("moneda"));
        datosPrestamo.setNroDocumento(resultSet.getString("nro_documento"));
        datosPrestamo.setNombreCompleto(resultSet.getString("nombre_completo"));

        return datosPrestamo;
    }
}
