import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * implementacionServidor
 */
public class implementacionServidor extends Thread {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int id;

    public implementacionServidor(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
        }
    }

    public void desconectar() {
        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        String accion = "";
        try {
            accion = dis.readUTF();
            if (accion.equals("Hola")) {
                System.out.println("El cliente con el id " + id + " Saluda");
                dos.writeUTF("Adios");
            }
        } catch (IOException ex) {
            Logger.getLogger(implementacionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
    }

}