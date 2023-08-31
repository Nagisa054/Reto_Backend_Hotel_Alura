package com.DAO;

import com.google.protobuf.DescriptorProtos;
import com.model.Reserva;
import com.views.RegistroHuesped;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     *
     * @return un Integer, el ID de la última reserva guardada en la base de datos.
     */
     public Integer getLastInsertId(){
         /*
         * Este método es usado por la clase HuespedController para
         * relacionar el registro del huesped y sú reserva.0
         */
         try {

         int resultado = 0;
         var query = "SELECT LAST_INSERT_ID(id) FROM reserva";

         PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         try(statement) {

             final ResultSet resultSet = statement.executeQuery();
             try(resultSet) {
                 while (resultSet.next()) resultado = resultSet.getInt(1);
             }
         }
    //         con.close();
             return resultado;
         }catch (SQLException e) {
             throw new RuntimeException(e);
         }
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

                // Se crea un objeto de tipo ResultSet
                final ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet) {

                    // A continuación se recorre el resultSet
                    while (resultSet.next()){
                        //sé asigna el id a "reserva". Este id es el valor de la primera columna del resultSet
                        reserva.setId(resultSet.getInt(1));

                        System.out.println(reserva);
                    }
                }
                // Se crea una ventana de Registro huéspedes y se muestra.
                RegistroHuesped registro = new RegistroHuesped();
                registro.setVisible(true);
            }
            con.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @return un List de Reserva
     */
    public List<Reserva> listar(){
        List<Reserva> resultado = new ArrayList<Reserva>();
        String query = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reserva";

        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()){
                Reserva fila = new Reserva(resultSet.getInt("id"),
                        resultSet.getString("fecha_entrada"),
                        resultSet.getString("fecha_salida"),
                        resultSet.getBigDecimal("valor"),
                        resultSet.getString("forma_pago"));
                resultado.add(fila);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
    public Integer editar(int id, String fechaEntrada, String fechaSalida, BigDecimal precio, String formaPago){
        String query = "UPDATE reserva SET " +
                "id = ?, fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?";
        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, fechaEntrada);
            statement.setString(3, fechaSalida);
            statement.setBigDecimal(4, precio);
            statement.setString(5, formaPago);
            statement.setInt(6, id);

            statement.execute();

            JOptionPane.showMessageDialog(null, "Registro Modificado Exitosa mente.");
            return statement.getUpdateCount();

        }catch (SQLException e){
                throw new RuntimeException(e);
            }
    }

    public Integer eliminar(int id){
        String query = "DELETE FROM reserva WHERE id = ?;";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);

            desactivarClaveForanea();
            statement.execute();
            activarClaveForanea();

            return statement.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void desactivarClaveForanea(){
        try (PreparedStatement statement = con.prepareStatement("SET FOREIGN_KEY_CHECKS = 0")) {
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void activarClaveForanea(){
        try (PreparedStatement statement = con.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")) {
            statement.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
