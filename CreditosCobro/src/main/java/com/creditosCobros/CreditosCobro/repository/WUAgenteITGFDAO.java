package com.creditosCobros.CreditosCobro.repository;

import com.creditosCobros.CreditosCobro.dto.PrestamoOperacionDTO;
import com.creditosCobros.CreditosCobro.exception.APIClientException;
import com.creditosCobros.CreditosCobro.exception.APIException;
import com.creditosCobros.CreditosCobro.exception.APIExceptionType;
import com.creditosCobros.CreditosCobro.exception.ErrorBody;
import com.creditosCobros.CreditosCobro.mapper.DatoPrestamoRowMapper;
import com.creditosCobros.CreditosCobro.model.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Repository
public class WUAgenteITGFDAO extends JdbcDaoSupport{

    public static final String SQL_OBTENER_PRESTAMO_POR_DOCUMENTO = "select pre.nro_cuenta nro_prestamo," +
            "       per.nro_documento nro_documento," +
            "       per.nom_completo nombre_completo," +
            "       fue_obt_des_moneda(pre.cod_moneda) moneda" +
            "  from pr_cta_prestamos pre," +
            "       ge_cta_clientes  cli," +
            "       ba_personas      per" +
            " where pre.cod_modulo = 6" +
            "   and pre.estado != 'N'" +
            "   and pre.nro_cuenta = cli.nro_cuenta" +
            "   and cli.relacion = 'P'" +
            "   and cli.cod_persona = per.cod_persona" +
            "   and ((nvl(?, 'x') = 'x') or (pre.cod_moneda = (?)))" +
            "   and nvl(pre.en_juicio,'N') = 'N'" +
            "   and per.cod_persona = ?";


    public DatosCliente obtenerDatoscliente(ConsultaCliente consultaCliente) throws APIException {
        String sql_datos_clientes = "{call IT.PAP_SER.PR_OBT_DAT_PERSONA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        if (consultaCliente.getCodigoPais() == null || consultaCliente.getCodigoTipo() == null
                || consultaCliente.getNroDocumento() == null) {
            return null;
        }
        log.info(sql_datos_clientes);
        log.info("params {}: ");
        log.info("p_cod_pais: " + consultaCliente.getCodigoPais());
        log.info("p_tip_documento: " + consultaCliente.getCodigoTipo());
        log.info("p_nro_documento: " + consultaCliente.getNroDocumento());
        log.info("p_tip_operacion: " + consultaCliente.getTipoOperacion());
        log.info("--------------------");
        try {
            DatosCliente datos = getJdbcTemplate().execute((CallableStatementCreator) con -> {
                        CallableStatement cStmt = con.prepareCall(sql_datos_clientes);
                        cStmt.setInt("p_cod_pais", consultaCliente.getCodigoPais());
                        cStmt.setInt("p_tip_documento", consultaCliente.getCodigoTipo());
                        cStmt.setString("p_nro_documento", consultaCliente.getNroDocumento());
                        cStmt.setString("p_tip_operacion", consultaCliente.getTipoOperacion());
                        if (consultaCliente.getFechaNacimiento() != null) {
                            cStmt.setDate("p_fec_nacimiento", new Date(consultaCliente.getFechaNacimiento().getTime()));
                        }
                        if (consultaCliente.getTelefono() != null) {
                            cStmt.setString("p_telefono", consultaCliente.getTelefono());
                        }
                        cStmt.registerOutParameter("p_cod_persona", Types.VARCHAR);
                        cStmt.registerOutParameter("p_pri_nombre", Types.VARCHAR);
                        cStmt.registerOutParameter("p_seg_nombre", Types.VARCHAR);
                        cStmt.registerOutParameter("p_pri_apellido", Types.VARCHAR);
                        cStmt.registerOutParameter("p_seg_apellido", Types.VARCHAR);
                        cStmt.registerOutParameter("p_cod_sexo", Types.VARCHAR);
                        cStmt.registerOutParameter("p_cod_pai_nacionalidad", Types.INTEGER);
                        cStmt.registerOutParameter("p_fec_nacimiento", Types.DATE);
                        cStmt.registerOutParameter("p_fec_vto_documento", Types.DATE);
                        cStmt.registerOutParameter("p_telefono", Types.VARCHAR);
                        cStmt.registerOutParameter("p_email", Types.VARCHAR);
                        cStmt.registerOutParameter("p_direccion", Types.VARCHAR);
                        cStmt.registerOutParameter("p_cod_profesion", Types.INTEGER);
                        cStmt.registerOutParameter("p_desc_prof", Types.VARCHAR);
                        cStmt.registerOutParameter("p_desc_prof_ing", Types.VARCHAR);
                        cStmt.registerOutParameter("p_cod_error", Types.INTEGER);
                        cStmt.registerOutParameter("p_msj_error", Types.VARCHAR);
                        return cStmt;
                    },
                    cs -> {
                        cs.execute();
                        int codError = cs.getInt("p_cod_error");
                        String menError = cs.getString("p_msj_error");
                        logger.info("error code: " + codError);
                        logger.info("error message: " + menError);

                        DatosCliente datoscliente = new DatosCliente();
                        datoscliente.setCodPersona(cs.getString("p_cod_persona"));
                        datoscliente.setPrimerNombre(cs.getString("p_pri_nombre"));
                        datoscliente.setSegundoNombre(cs.getString("p_seg_nombre"));
                        datoscliente.setPrimerApellido(cs.getString("p_pri_apellido"));
                        datoscliente.setSegundoApellido(cs.getString("p_seg_apellido"));
                        datoscliente.setSexo(cs.getString("p_cod_sexo"));
                        datoscliente.setPaisNacionalidad(cs.getInt("p_cod_pai_nacionalidad"));
                        datoscliente.setFechaNacimiento(cs.getDate("p_fec_nacimiento"));
                        datoscliente.setFechaVencimiento(cs.getDate("p_fec_vto_documento"));
                        datoscliente.setTelefono(cs.getString("p_telefono"));
                        datoscliente.setEmail(cs.getString("p_email"));
                        datoscliente.setDireccion(cs.getString("p_direccion"));
                        datoscliente.setCodOcupacion(cs.getInt("p_cod_profesion"));
                        datoscliente.setCodProfesion(cs.getInt("p_cod_profesion"));
                        datoscliente.setDescProfesion(cs.getString("p_desc_prof"));
                        datoscliente.setDescProfesionIng(cs.getString("p_desc_prof_ing"));
                        datoscliente.setCodError(codError);
                        datoscliente.setMensaje(menError);

                        return datoscliente;
                    }
            );
//            if (datos.getCodError() != 0) {
//                throw new API(Code.ERROR_ITGF.errorCode, datos.getMensaje());
//            }
            return datos;
        }
        catch (DataAccessException e) {
            logger.error("Ocurrio un error al obtener los datos del cliente :( ", e);
            return null;
        }

    }
    public String obtenerMonedaBcp(Integer codMoneda) {
        final String CALL_OBTENER_MONEDA_BCP = "{? = call FUE_OBT_MON_BCP(?)}";

        String result = getJdbcTemplate().execute((CallableStatementCreator) con -> {
            logger.info(CALL_OBTENER_MONEDA_BCP);
            logger.info("params: {}");
            logger.info("COD_MONEDA_BCP: " + codMoneda);


            CallableStatement cStmt = con.prepareCall(CALL_OBTENER_MONEDA_BCP);
            cStmt.registerOutParameter(1, Types.VARCHAR);
            cStmt.setInt(2, codMoneda);

            return cStmt;
        }, cs -> {
            cs.execute();
            return cs.getString(1);
        });
        logger.info("resul: " + result);
        return result;
    }
    public DeudaVencida getDeudaVencidaByNroCuentaAndMoneda(String nroCuenta, int moneda)
            throws SQLDataException {
        String deudaDePrestamoString = this.obtenerDeudaPrestamo(nroCuenta, moneda);
        return getDeudaVencidaFromString(nroCuenta, deudaDePrestamoString);
    }
    private DeudaVencida getDeudaVencidaFromString(String nroCuenta, String deudaDePrestamoString) throws SQLDataException {
        try {
            if (deudaDePrestamoString.contains("Transaccion ya registrada")) {
                throw new SQLDataException("Transaccion ya registrada");
            }
            if (StringUtils.isEmpty(nroCuenta) || StringUtils.countOccurrencesOf(deudaDePrestamoString, ";") != 3) {
                logger.info(deudaDePrestamoString);
                throw new SQLDataException("La respuesta de la función no tiene el formato: nro_cuenta;nro_cuota;" +
                        "fec_vencimiento;monto_total_cobrar; Responde con el mensaje: " + deudaDePrestamoString);
            }
            String[] splitted = deudaDePrestamoString.split(";");
            DeudaVencida deudaVencida = new DeudaVencida();
            deudaVencida.setNroCuenta(splitted[0]);

            deudaVencida.setNroCuota(Integer.valueOf(splitted[1]));

            if (splitted[2].length() != 8) {
                throw new SQLDataException("La fecha no se encuentra en el formato correcto");
            }
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
            java.util.Date fechaV = format.parse(splitted[2]);
            deudaVencida.setFechaVencimiento(fechaV);
            if (splitted[3] == null || splitted[3].isEmpty()) {
                logger.error("El monto no puede ser nulo, o vacio");
                throw new SQLDataException();
            }
            deudaVencida.setMontoTotalCobrar(new BigDecimal(splitted[3].replace(",", ".")));

            return deudaVencida;
        } catch (DataAccessException e) {
            log.error("Error al obtener deudas de la cuenta nro: {}", nroCuenta);
            throw e;
        } catch (ParseException e) {
            log.error("Error al parsear la fecha de vencimiento {}", deudaDePrestamoString);
            throw new SQLDataException();
        }
    }
    public String obtenerDeudaPrestamo(String nroPrestamo, int moneda) {
        final String CALL_OBTENER_DEUDA = "{? = call it.pap_ser.fu_obt_deu_prest_cta(?,?)}";
        try {
            String result = getJdbcTemplate().execute((CallableStatementCreator) con -> {
                logger.info(CALL_OBTENER_DEUDA);
                logger.info("p_nro_cuenta: " + nroPrestamo);
                logger.info("p_moneda: " + moneda);

                CallableStatement cStmt = con.prepareCall(CALL_OBTENER_DEUDA);
                cStmt.registerOutParameter(1, Types.VARCHAR);
                cStmt.setString(2, nroPrestamo);
                cStmt.setInt(3, moneda);
                return cStmt;
            }, cs -> {
                cs.execute();
                return cs.getString(1);
            });
            logger.info("result: {}");
            return result;
        } catch (DataAccessException e) {
            logger.error("Error al obtener deudas de la cuenta nro: {}");
            throw e;
        }
    }
    public OperacionesResponse obtenerPrestamosPorDocumento(ConsultarPrestamoDoc documento, Integer moneda) throws APIException {

        OperacionesResponse operacionesResponse = new OperacionesResponse();
        List<PrestamoOperacionDTO> listOpDTO = new ArrayList<PrestamoOperacionDTO>();

        /* Obtenemos datos del cliente */
        ConsultaCliente consultaCliente = new ConsultaCliente();
        consultaCliente.setCodigoPais(documento.getCodigoPais());
        consultaCliente.setCodigoTipo(documento.getCodigoTipoDoc());
        consultaCliente.setNroDocumento(documento.getNumeroDocumento());
        DatosCliente datosCliente = obtenerDatoscliente(consultaCliente);

        if (datosCliente == null) {
            throw new APIException(APIExceptionType.DATABASE, "Error al obtener datos del cliente para obtener datos de prestamo");
        }

        /* Obtenemos la moneda por codigo */
        String mon = obtenerMonedaBcp(moneda);

        /* Obtenemos datos del prestamo */
        List<DatosPrestamo> listPrestamo = new ArrayList<>();
        try {
            listPrestamo = getJdbcTemplate().query(SQL_OBTENER_PRESTAMO_POR_DOCUMENTO, new DatoPrestamoRowMapper(), mon, mon, datosCliente.getCodPersona());
        } catch (DataAccessException e) {
            logger.error("Ocurrio un error al obtener datos de prestamo", e);
           // throw new BantotalClienteException(Code.ERROR_ITGF.errorCode, "Ocurrio un error al obtener datos de prestamos");
        }

        operacionesResponse.setNombre(datosCliente.getPrimerNombre() + " " + (datosCliente.getSegundoNombre() != null ? datosCliente.getSegundoNombre() : ""));
        operacionesResponse.setApellido(datosCliente.getPrimerApellido() + " " + (datosCliente.getSegundoApellido() != null ? datosCliente.getSegundoApellido() : ""));

        for (DatosPrestamo p : listPrestamo) {
            DeudaVencida deudaVencida;
            try {
                deudaVencida = this.getDeudaVencidaByNroCuentaAndMoneda(p.getNroPrestamo(), moneda);
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
            PrestamoOperacionDTO prestamoOperacionDTO = new PrestamoOperacionDTO();
            prestamoOperacionDTO.setNumeroOperacion(Long.valueOf(p.getNroPrestamo()));
            prestamoOperacionDTO.setMonedaLetras(p.getMoneda());
            prestamoOperacionDTO.setMoneda(moneda);
            prestamoOperacionDTO.setFechaVencimiento(deudaVencida.getFechaVencimiento());
            prestamoOperacionDTO.setSaldo(deudaVencida.getMontoTotalCobrar());
            listOpDTO.add(prestamoOperacionDTO);
        }

        operacionesResponse.setOperaciones(listOpDTO);

        return operacionesResponse;
    }
}
