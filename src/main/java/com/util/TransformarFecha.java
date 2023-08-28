package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta clase se encarga de transformar el formato de las Fechas
 *
 * @since 28/08/2023
 * @author Juan Pablo Rojas Cardozo | Nagisa054
 */
public class TransformarFecha {
    private String f;
    private SimpleDateFormat formatoNuevo;

    /**
     * Transforma el formato de una fecha de tipo Date
     * @param fecha
     * @return  Un String con la fecha en formato yyyy-MM-dd
     */
    public String trasformar(Date fecha){
        formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");

        f = formatoNuevo.format(fecha);

        return f;
    }
}
