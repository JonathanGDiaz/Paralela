import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class implementacionChatCliente extends UnicastRemoteObject implements chatCliente, Runnable {
    chatServidor servidor;
    public String nombre = null;

    implementacionChatCliente(String nombre, chatServidor servidor) throws RemoteException {
        this.nombre = nombre;
        this.servidor = servidor;
        servidor.registro(this);
    }

    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        String mensaje;
        while (true) {
            mensaje = s.nextLine();
            try {
                servidor.mensaje(nombre + " : " + mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mensajeCliente(String mensaje) throws RemoteException {
        System.err.println(mensaje);
    }

}
