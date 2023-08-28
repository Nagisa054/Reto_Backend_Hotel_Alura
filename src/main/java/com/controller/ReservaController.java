package com.controller;

import com.DAO.ReservaDAO;
import com.factory.ConnectionFactory;
import com.model.Reserva;
import com.util.CalcPrecio;
import com.util.TransformarFecha;
import com.views.ReservasView;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;

public class ReservaController {
    private Connection con;
    private ReservaDAO reservaDAO;
    private Reserva reserva;
    private String fpago;
    private BigDecimal precio;



    public ReservaController() {
        con = new ConnectionFactory().tryConnection();
        reservaDAO = new ReservaDAO(con);

    }
    public void reservar(Date fechaEntrada, Date fechaSalida, Integer formaPago){
        if (formaPago < 1) fpago = "Tarjeta de Crédito";
        if (formaPago == 1) fpago = "Tarjeta de Débito";
        if (formaPago > 1) fpago = "Dinero en efectivo";

        precio = new CalcPrecio().calcularPorDias(fechaEntrada, fechaSalida);

        String fechaE = new TransformarFecha().trasformar(fechaEntrada);
        String fechaS = new TransformarFecha().trasformar(fechaSalida);

        reserva = new Reserva(fechaE, fechaS, precio,fpago);
        reservaDAO.guardar(reserva);
    }
}
