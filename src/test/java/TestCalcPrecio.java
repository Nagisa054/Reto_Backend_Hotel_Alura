import com.util.CalcPrecio;

import java.math.BigDecimal;
import java.util.Date;

public class TestCalcPrecio {
    public static void main(String[] args) {

        //el precio por noche es de 170

        Date fecha1 = new Date(2023, 8, 28 );
        Date fecha2 = new Date(2023, 8, 30);
        BigDecimal precio = new CalcPrecio().calcularPorDias(fecha1, fecha2);

        System.out.println("el precio es: $" + precio);
    }

}
