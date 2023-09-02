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
import java.util.List;

/**
 * Esta clase se encarga de gestionar las acciones de la clase ReservasView.
 *<br><br>
 * De momento solo maneja la acción de Reservar. Si en el futuro se
 * agregan más opciones, esta clase se encargará de comunicar
 * la lógica con la vista
 *
 * @since 28/08/2023
 * @author Juan Pablo Rojas | Nagisa054
 */
public class ReservaController {
    private Connection con;
    private ReservaDAO reservaDAO;
    private Reserva reserva;
    private String fpago;
    private BigDecimal precio;


    /**
     * Al momento de construir un objeto de esta clase, se crean dos instancias
     * una de conexión con la base de datos y otra de la clase ReservaDAO.
     */
    public ReservaController() {
        con = new ConnectionFactory().tryConnection();
        reservaDAO = new ReservaDAO(con);

    }

    /**
     *
     * @param fechaEntrada
     * @param fechaSalida
     * @param formaPago
     */
    public void reservar(Date fechaEntrada, Date fechaSalida, Integer formaPago) {

        /*
         * Sé escoge la forma de pago de acuerdo al valor de "formaPago".
         * Sí "formaPago" es igual a 0, entonces es "Tarjeta de crédito",
         * Sí "formaPago" es igual a 1, entonces es "Tarjeta de Débito",
         * Sí "formaPago" es igual a 2, entonces es "Dinero en Efectivo".
         */
        if (formaPago < 1) fpago = "Tarjeta de Crédito";
        if (formaPago == 1) fpago = "Tarjeta de Débito";
        if (formaPago > 1) fpago = "Dinero en efectivo";


        // * Aquí se calcula el precio de acuerdo a la cantidad de días.
        precio = new CalcPrecio().calcularPorDias(fechaEntrada, fechaSalida);


        // * Sé cambia el formato de las fechas a: yyyy-MM-dd
        String fechaE = new TransformarFecha().trasformar(fechaEntrada);
        String fechaS = new TransformarFecha().trasformar(fechaSalida);

        // * Se crea un nuevo objeto de tipo Reserva.
        reserva = new Reserva(fechaE, fechaS, precio,fpago);

        // *  el objeto reserva es enviado como parametro al método "guardar" de ReservaDAO
       this.reservaDAO.guardar(reserva);
    }

    /**
     *
     * @return Un List de Reserva
     */
    public List<Reserva> listar(){
        return this.reservaDAO.listar();
    }

    /**
     *
     * @param id
     * @param fechaEntrada
     * @param fechaSalida
     * @param precio
     * @param formaPago
     * @return Un Integer Con la cantidad de registros modificados
     */
    public int editar(int id, String fechaEntrada, String fechaSalida, BigDecimal precio, String formaPago){
        return this.reservaDAO.editar(id, fechaEntrada, fechaSalida, precio, formaPago);
    }

    /**
     *
     * @param id
     * @return un Integer
     */
    public int eliminar(int id){
        return this.reservaDAO.eliminar(id);
    }
}
