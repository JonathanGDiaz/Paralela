import java.io.IOException;
import java.util.ArrayList;

/**
 * Cliente
 */
public class Cliente {
    static final int MAX_HILOS = 10;
    public static final String HOST = "192.168.43.193";

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Thread> clientes = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clientes.add(new implementacionCliente(i));
        }
        for (Thread thread : clientes) {
            thread.start();
        }
    }

}