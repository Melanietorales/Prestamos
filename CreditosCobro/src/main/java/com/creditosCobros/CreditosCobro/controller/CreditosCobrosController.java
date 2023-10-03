package com.creditosCobros.CreditosCobro.controller;

import com.creditosCobros.CreditosCobro.constants.ApiConstants;
import com.creditosCobros.CreditosCobro.model.PrestamosPersona;
import com.creditosCobros.CreditosCobro.service.CreditosCobroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.creditosCobros.CreditosCobro.exception.APIException;

@Api
@RestController
@RequestMapping("/prestamos")
public class CreditosCobrosController {

    CreditosCobroService creditosCobroService;
    private static final String API_KEY_DOC = "API KEY de la aplicación solicitante. Este parámetro se puede pasar a través de un HEADER con el nombre: X-RshkMichi-ApiKey";
    private static final String CONSULTAR = "/consultar-prestamo";
    private static final String COBRAR = "/cobrar-prestamo";
    private static final String CONFIRMAR = "/{token}/confirmar";

    @RequestMapping(value = CONSULTAR, method = RequestMethod.GET)
   // @AclAction(actionCode = ApiConstants.ACTION_CONSULTAR_PRESTAMOS)
    @ApiOperation("Api que consulta datos de prestamos")
    public PrestamosPersona consultarPrestamos(
          //  @ApiParam(value = API_KEY_DOC, required = false)
            //@RequestParam(value = "api_key", required = false) String apiKey,
            @RequestParam(value = ApiConstants.PARAM_MONEDA, required = true) Integer moneda,
            @RequestParam(value = ApiConstants.PARAM_NUMERO_DOC, required = false) String numeroDocumento,
            @RequestParam(value = ApiConstants.PARAM_PAIS_DOC, required = false) Integer paisDocumento,
            @RequestParam(value = ApiConstants.PARAM_TIPO_DOC, required = false) Integer tipoDocumento,
            @RequestParam(value = ApiConstants.PARAM_CUENTA, required = false) Integer cuenta) throws APIException {

        if (cuenta==null){
            return creditosCobroService.consultarPrestamos(paisDocumento, tipoDocumento, numeroDocumento, moneda);
        }
        return  creditosCobroService.consultarPrestamos(cuenta, moneda);
    }
}
