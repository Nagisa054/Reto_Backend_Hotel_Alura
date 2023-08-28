package com.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase calcula el precio de la reserva.<br>
 * también puede ser usada para calcular el precio de otras cosas.
 *
 * @since 28/08/2023
 * @author Juan Pablo Rojas Cardozo | Nagisa054
 */
public class CalcPrecio {
    private BigDecimal precioNoche = BigDecimal.valueOf(170);

    /**
     * Calcula el precio de la reserva de acuerdo a la cantidad de días.
     * Precio base es de 170.
     *
     * @param fechaEntrada
     * @param fechaSalida
     * @return El precio en BidDecimal
     */
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
