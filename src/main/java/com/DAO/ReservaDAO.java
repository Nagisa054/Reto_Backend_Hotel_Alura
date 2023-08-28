package com.DAO;

import com.model.Reserva;
import com.util.TransformarFecha;
import com.views.RegistroHuesped;

import java.sql.*;

/**
 * Aquí se maneja toda la lógica relacionada con la tabla "Reserva" de la base de datos.
 * @since 28/08/2023
 * @author Juan Pablo Rojas | Nagisa054
 */
public class ReservaDAO {
    final private Connection con;

    /**
     * @param connection
     */
    public ReservaDAO(Connection connection){
        this.con = connection;

    }

    /**
     * Aquí se crean y guardan los registros de las reservas.
     * @param reserva
     */
    public void guardar(Reserva reserva){
        // consulta SQL sin los valores
        try{
            var query = "INSERT INTO reserva" +
                    "(fecha_entrada, fecha_salida, valor, forma_pago)" +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try(statement) {
                //Asignación de valores a la consulta
                statement.setString(1, reserva.getFechaEntrada());
                statement.setString(2, reserva.getFechaSalida());
                statement.setBigDecimal(3, reserva.getPrecio());
                statement.setString(4, reserva.getFormaPago());
                //Sé ejecuta la consulta
                statement.execute();

                System.out.println(query + "\n"
                                + reserva.getFechaEntrada() + "\n"
                                + reserva.getFechaSalida() + "\n"
                                + reserva.getPrecio()  + "\n"
                                + reserva.getFormaPago()  + "\n");

                // Se crea un objeto de tipo ResultSet
                final ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet) {

                    // A continuación se recorre el resultSet
                    while (resultSet.next()){
                        //sé asigna el id a "reserva". Este id es el valor de la primera columna del resultSet
                        reserva.setId(resultSet.getInt(1));

                        System.out.println("Se a creado la reserva: " + reserva );
                    }
                }
                // Se crea una ventana de Registro huéspedes y se muestra.
                RegistroHuesped registro = new RegistroHuesped();
                registro.setVisible(true);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
