import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implementacionCliente
 */
public class implementacionCliente extends Thread {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int id;
    private final int PUERTO = 1111;
    private final String HOST = "192.168.43.193";

    public implementacionCliente(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(HOST, PUERTO);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            System.out.println(id + " envia un mensaje");
            dos.writeUTF("Hola");
            String respuesta = dis.readUTF();
            System.out.println(id + " servidor devuelve saludo: " + respuesta);
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(implementacionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}