package com.DAO;

import com.model.Huespedes;
import com.views.MenuUsuario;

import javax.swing.*;
import java.sql.*;

public class HuespedDAO {

    final private Connection con;

    public HuespedDAO(Connection connection) {
        this.con = connection;
    }

    public void registrar(Huespedes huesped){
        try {
            //se crea la consulta SQL
            var query = "INSERT INTO huespedes " +
                    "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id)" +
                    "VALUES (?,?,?,?,?,?)";

            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try(statement) {
                //Asignación de valores a la query
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setString(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getReservaId());

                /*
                 Se ejecuta la query y se crea un objeto de tipo ResultSet el cual será
                 recorrido con un bucle while para obtener el valor de la primera columna (id)
                 del último registro guardado en la base de datos, luego este valor (id)
                 es asignado al objeto huesped.
                */
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet) {
                    while(resultSet.next()){
                        huesped.setId(resultSet.getInt(1));
                        System.out.println(huesped);

                    }
                    JOptionPane.showMessageDialog(null, "Registro Guardado.");

                    // se crea un menu y se hace visible
                    MenuUsuario menu = new MenuUsuario();
                    menu.setVisible(true);
                }
            }
//            con.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
