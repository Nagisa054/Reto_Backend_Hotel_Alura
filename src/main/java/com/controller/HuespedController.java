package com.controller;

import com.DAO.HuespedDAO;
import com.DAO.ReservaDAO;
import com.factory.ConnectionFactory;
import com.model.Huespedes;

import java.sql.Connection;

/**
 * Esta clase se encarga de comunicar la parte visual con de
 * RegistroHuespedes con HuespedesDAO.
 *<br><br>
 *
 * @since 28/08/2023
 * @author Juan Pablo Rojas | Nagisa054
 */

public class HuespedController {
    private Connection con;
    private HuespedDAO huespedDAO;
    private Huespedes huesped;


    /**
     * Se crean dos instancias una de conexión
     * con la base de datos y otra de la clase HuespedDAO.
     */
    public HuespedController() {
        this.con = new ConnectionFactory().tryConnection();
        this.huespedDAO = new HuespedDAO(con);
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @param fNacimiento
     * @param nacionalidad
     * @param telefono
     * @param resId
     */
    public void registrar(String nombre, String apellido, String fNacimiento, String nacionalidad, String telefono, Integer resId){
        huesped = new Huespedes(nombre,apellido, fNacimiento, nacionalidad, telefono, resId);
        huespedDAO.registrar(huesped);
    }

    /**
     *
     * @return un Integer con el último Id generando automáticamente
     * en la tabla Huespedes de la base de datos.
     */
    public Integer getLastReservaId(){
        ReservaDAO reservaDAO = new ReservaDAO(new ConnectionFactory().tryConnection());
        return reservaDAO.getLastInsertId();
    }

}
