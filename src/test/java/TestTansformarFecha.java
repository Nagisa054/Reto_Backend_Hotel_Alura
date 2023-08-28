import com.util.TransformarFecha;

import java.util.Date;

public class TestTansformarFecha {
    public static void main(String[] args) {
        String fechaTransformada;
        long milisegundos = System.currentTimeMillis();
        Date fecha = new Date(milisegundos);

        fechaTransformada = new TransformarFecha().trasformar(fecha);

        System.out.println("original: " + fecha);
        System.out.println("transformado: " + fechaTransformada);
    }
}
