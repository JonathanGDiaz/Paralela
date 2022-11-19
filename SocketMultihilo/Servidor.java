import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor
 */
public class Servidor {
    public static final int PUERTO = 1111;

    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        System.out.println("Abriendo el changarro");
        try {
            ss = new ServerSocket(PUERTO);
            System.out.println("Changarro abierto");
            int id = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Conexion entrante " + socket);
                ((implementacionServidor) new implementacionServidor(socket, id)).start();
                id++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}