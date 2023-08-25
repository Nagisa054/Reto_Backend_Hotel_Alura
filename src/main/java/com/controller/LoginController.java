package com.controller;

import com.DAO.EmpleadoDAO;
import com.factory.ConnectionFactory;

import java.sql.Connection;

/**
 * Esta clase se encarga de gestionar las acciones de la clase Login.
 *<br><br>
 * De momento solo maneja la acción de login. Si
 * en el futuro se agrega una opción para agregar
 * empleados, esta clase se encargará de comunicar
 * la lógica con la vista
 *
 * @since 24/08/2023
 * @author Juan Pablo Rojas | Nagisa054
 */
public class LoginController {
    private EmpleadoDAO empleadoDAO;
    private Connection con;

    /**
     * Al momento de construir un objeto de esta clase, se crean dos instancias
     * una de conexión con la base de datos y otra de la clase EmpleadoDAO.
     */
    public LoginController(){
        con = new ConnectionFactory().tryConnection();
        this.empleadoDAO = new EmpleadoDAO(con);
    }

    /**
     * @param nombre
     * @param clave
     * @return Empleado.Login()
     */
    public Boolean login(String nombre, String clave) {
        //comunica la lógica de EmpleadoDAO con la vista
        return empleadoDAO.Login(nombre, clave);
    }


}
