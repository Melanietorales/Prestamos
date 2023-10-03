package com.creditosCobros.CreditosCobro.service;

import com.creditosCobros.CreditosCobro.exception.APIException;
import com.creditosCobros.CreditosCobro.model.PrestamosPersona;

public interface CreditosCobroService {
PrestamosPersona consultarPrestamos(Integer paisDocumento,
                                    Integer tipoDocumento,
                                    String numeroDocumento,
                                    Integer moneda) throws APIException;
PrestamosPersona consultarPrestamos(Integer cuenta, Integer moneda) throws APIException;
}
