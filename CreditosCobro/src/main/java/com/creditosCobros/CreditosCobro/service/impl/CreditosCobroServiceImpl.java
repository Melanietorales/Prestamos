package com.creditosCobros.CreditosCobro.service.impl;

import com.creditosCobros.CreditosCobro.config.BTConfigData;
import com.creditosCobros.CreditosCobro.dto.PrestamoOperacionDTO;
import com.creditosCobros.CreditosCobro.exception.APIException;
import com.creditosCobros.CreditosCobro.exception.APIExceptionType;
import com.creditosCobros.CreditosCobro.model.*;
import com.creditosCobros.CreditosCobro.repository.WUAgenteITGFDAO;
import com.creditosCobros.CreditosCobro.service.CreditosCobroService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CreditosCobroServiceImpl implements CreditosCobroService {

//    private final CanalDigital canalDigital;
private BTConfigData btConfigData;

private WUAgenteITGFDAO wuAgenteITGFDAO;

    private OperacionesResponse descartarPrestamosCanceladosAntesCierre(OperacionesResponse prestamos){
        if(prestamos!=null && prestamos.getOperaciones()!=null){
            List<PrestamoOperacionDTO> noCancelados = new ArrayList<>();
            for (PrestamoOperacionDTO prestamo : prestamos.getOperaciones()) {
                if(BigDecimal.ZERO.compareTo(prestamo.getSaldo())!=0){
                    noCancelados.add(prestamo);
                }
            }
            prestamos.setOperaciones(noCancelados); //Se actualiza la lista de operaciones con los no cancelados.
        }
        return prestamos;
    }

@Override
public PrestamosPersona consultarPrestamos(Integer paisDocumento, Integer tipoDocumento, String numeroDocumento,
                                           Integer moneda) throws APIException {
    try {
        log.info("Obtener datos de prestamo del cliente");
        ConsultarPrestamoDoc documento = new ConsultarPrestamoDoc();
        documento.setCodigoPais(paisDocumento);
        documento.setCodigoTipoDoc(tipoDocumento);
        documento.setNumeroDocumento(numeroDocumento);
        OperacionesResponse operaciones = descartarPrestamosCanceladosAntesCierre(wuAgenteITGFDAO.obtenerPrestamosPorDocumento(documento, moneda));
    } catch (APIException e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public PrestamosPersona consultarPrestamos(Integer cuenta, Integer moneda) throws APIException {
        return null;
    }
}
