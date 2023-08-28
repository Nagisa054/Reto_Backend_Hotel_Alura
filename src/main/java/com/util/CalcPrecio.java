package com.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalcPrecio {
    private BigDecimal precioNoche = BigDecimal.valueOf(170);

    public BigDecimal calcularPorDias(Date fechaEntrada, Date fechaSalida){
        Long a = Math.abs(fechaSalida.getTime() - fechaEntrada.getTime());
        BigDecimal dias = BigDecimal.valueOf(TimeUnit.DAYS.convert(a, TimeUnit.MILLISECONDS));

        return precioNoche.multiply(dias);

    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }
}
