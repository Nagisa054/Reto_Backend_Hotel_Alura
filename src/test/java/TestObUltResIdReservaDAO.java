import com.DAO.ReservaDAO;
import com.factory.ConnectionFactory;
import com.model.Reserva;

import java.math.BigDecimal;

public class TestObUltResIdReservaDAO {
    public static void main(String[] args) {
        BigDecimal precio = BigDecimal.valueOf(170);
        ReservaDAO reservaDAO = new ReservaDAO(new ConnectionFactory().tryConnection());

        Reserva reserva1 = new Reserva("2023-08-29", "2023-08-23", precio, "Dinero en efectivo");
        reservaDAO.guardar(reserva1);
        System.out.println(" === " + reservaDAO.getLastInsertId() + " === ");

        Reserva reserva2 = new Reserva("2023-08-29", "2023-08-23", precio, "Dinero en efectivo");
        reservaDAO.guardar(reserva2);
        System.out.println(" === " + reservaDAO.getLastInsertId() + " === ");

    }
}
