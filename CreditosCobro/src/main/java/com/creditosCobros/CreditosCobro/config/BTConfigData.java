package com.creditosCobros.CreditosCobro.config;

import org.springframework.beans.factory.InitializingBean;

public class BTConfigData implements InitializingBean {
    private static final String TRUE = "true";
    private static BTConfigData instance;

    //habilitacion Bantotal
    private String bantotalActivo;

    public BTConfigData(){
    }

    public BTConfigData(String bantotalActivo) {
        super();
        this.bantotalActivo = bantotalActivo;
    }

    /**
     * @return the bantotalActivo
     */
    public String getBantotalActivo() {
        return bantotalActivo;
    }

    public Boolean isBantotalActivo(){
        return TRUE.equalsIgnoreCase(this.bantotalActivo);
    }

    /**
     * @param bantotalActivo the bantotalActivo to set
     */
    public void setBantotalActivo(String bantotalActivo) {
        this.bantotalActivo = bantotalActivo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    public static BTConfigData getInstance(){
        return instance;
    }
}
