package com.creditosCobros.CreditosCobro.service.impl;

import com.creditosCobros.CreditosCobro.config.BTConfigData;
import com.creditosCobros.CreditosCobro.dto.CuotaPrestamoDTO;
import com.creditosCobros.CreditosCobro.dto.CuotaPrestamoPrevDTO;
import com.creditosCobros.CreditosCobro.dto.DetallePrestamoDTO;
import com.creditosCobros.CreditosCobro.dto.PrestamoOperacionDTO;
import com.creditosCobros.CreditosCobro.exception.APIException;
import com.creditosCobros.CreditosCobro.model.*;
import com.creditosCobros.CreditosCobro.repository.WUAgenteITGFDAO;
import com.creditosCobros.CreditosCobro.service.CreditosCobroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private void ordenarPorFechaVencimiento(List<PrestamoOperacionDTO> prestamos){
        if(prestamos!=null){
            Collections.sort(prestamos, Comparator.comparing(o1->o1.getFechaVencimiento()));
        }
    }

    private String cambiarFormatoFecha(Date date) {
        if (date== null) {
            return "";
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        return sdf2.format(date);
    }

    private List<CuotaModel> parseCuotas(DetallePrestamoDTO detallePrestamoDTO){

        List<CuotaModel> cuotas = new ArrayList<>();
        for (CuotaPrestamoDTO pc: detallePrestamoDTO.getCuotasPrestamo()) {
            CuotaModel cuota = new CuotaModel();
            cuota.setFechaVencimiento(cambiarFormatoFecha(pc.getPrev().getFechaVencimiento()));
            cuota.setNumeroCuota(pc.getPrev().getNumeroCuota());
            cuota.setMontoPagar(pc.getPrev().getImporteAPagar());
            cuotas.add(cuota);
        }
        return cuotas;
    }

    private Date vencimientoCuota(DetallePrestamoDTO detallePrestamoDTO){
        if (detallePrestamoDTO.getCuotasPrestamo()!=null){
            return detallePrestamoDTO.getCuotasPrestamo().get(0).getPrev().getFechaVencimiento();
        }
        return null;
    }
    private void ordenarPorFechaVencimientoCuota(List<PrestamoModel> prestamos){
        if(prestamos!=null){
            Collections.sort(prestamos, Comparator.comparing(o1->o1.getFechaVencimientoCuota()));
        }
    }
    private PrestamosPersona parsePrestamoPersona(OperacionesResponse operaciones) {


        PrestamosPersona prestamosPersona = new PrestamosPersona();
        if (btConfigData.isBantotalActivo() && operaciones.getCuenta()==null){ //Preguntar si se borra esta linea
            prestamosPersona.setNombres(PrestamosPersona.NOCTA);
            return prestamosPersona;
        }

        prestamosPersona.setNumeroCuenta(operaciones.getCuenta());
        prestamosPersona.setApellidos(operaciones.getApellido());
        prestamosPersona.setNombres(operaciones.getNombre());

        List<PrestamoModel> prestamoList = new ArrayList();
        for (PrestamoOperacionDTO o : operaciones.getOperaciones()) {

            PrestamoModel p = new PrestamoModel();
            p.setCantidadCuotas(o.getDetalle().getCantidadCuotas());
            p.setNumeroPrestamo(o.getNumeroOperacion());
            p.setModulo(o.getModulo());
            p.setMoneda(o.getMoneda());
            p.setMonedaLetras(o.getMonedaLetras());
            p.setSucursal(o.getSucursal());
            p.setPapel(o.getPapel());
            p.setOperacion(o.getNumeroOperacion());
            p.setSubCuenta(o.getSubOperacion());
            p.setTipoOperacion(o.getTipoOperacion());
            p.setFechaVencimiento(cambiarFormatoFecha(o.getFechaVencimiento()));
            p.setMonto(o.getSaldo());
            p.setCuotas(parseCuotas(o.getDetalle()));
            p.setFechaVencimientoCuota(vencimientoCuota(o.getDetalle()));
            prestamoList.add(p);
        }
        ordenarPorFechaVencimientoCuota(prestamoList);
        prestamosPersona.setPrestamo(prestamoList);

        return prestamosPersona;
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
        ordenarPorFechaVencimiento(operaciones.getOperaciones());
        return parsePrestamoPersona(operaciones);
    } catch (APIException e) {
        throw new RuntimeException(e);
    }
}

    @Override
    public PrestamosPersona consultarPrestamos(Integer cuenta, Integer moneda) throws APIException {
        return null;
    }
}
