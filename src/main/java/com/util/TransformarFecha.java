package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformarFecha {
    private String f;
    private SimpleDateFormat formatoNuevo;
    public String trasformar(Date fecha){
        formatoNuevo = new SimpleDateFormat("yyy-MM-dd");

        f = formatoNuevo.format(fecha);

        return f;
    }
}
