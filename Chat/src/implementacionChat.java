import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class implementacionChat extends UnicastRemoteObject implements chatServidor {
    public ArrayList<chatCliente> clientes;

    public implementacionChat() throws RemoteException {
        clientes = new ArrayList<chatCliente>();
    }

    @Override
    public void registro(chatCliente cliente) throws RemoteException {
        this.clientes.add(cliente);
    }

    @Override
    public void mensaje(String mensaje) throws RemoteException {
        int a = 0;
        while (a < clientes.size()) {
            clientes.get(a++).mensajeCliente(mensaje);
        }
    }

}
