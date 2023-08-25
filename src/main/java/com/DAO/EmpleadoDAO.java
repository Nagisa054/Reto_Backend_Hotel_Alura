package com.DAO;

import com.views.MenuUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Aquí se maneja la lógica del Login
 * a demás del CRUD de tabla Empleado.
 *
 * @since 24/08/2023
 * @author Juan Pablo Rojas | Nagisa054
 */
public class EmpleadoDAO {
    final private Connection con;

    /**
     * Al momento de ser construido un objeto de esta clase
     * es necesario pasar una conexión con la base de datos
     * para sí poder realizar las consultas necesarias.
     *
     * @param connection
     */
    public EmpleadoDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Compara los parametros con los datos de la base de datos hotel_alura_db.
     * @param usuario
     * @param clave
     * @return true sí hay coincidencias con los datos en la base de datos,
     * false si no hay coincidencias.
     */
    public Boolean Login(String usuario, String clave) {
        try {
            var query = "SELECT nombre_en_sistema, clave_en_sistema FROM Empleado"
                        +" WHERE nombre_en_sistema = '"+usuario+"'"
                        +" AND clave_en_sistema = '"+clave+"'";
            final PreparedStatement statement = con.prepareStatement(query);
            System.out.println(query);
            try (statement) {
                final ResultSet resultSet = statement.executeQuery();
                System.out.println();

                while (resultSet.next()) {
                    String nombreDB = resultSet.getString("nombre_en_sistema");
                    String claveDB = resultSet.getString("clave_en_sistema");

                    if (usuario.equals(nombreDB) && clave.equals(claveDB)) {
                        MenuUsuario menu = new MenuUsuario();
                        menu.setVisible(true);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
        return false;
    }
}




