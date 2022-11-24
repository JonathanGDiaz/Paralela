package Cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Servidor.contadorServidor;

/**
 * Main
 */
public class primerCliente {
    public static void main(String[] args) {
        try {
            Registry rmii = LocateRegistry.getRegistry("192.168.43.122", 1111);
            try {
                contadorServidor servidor = (contadorServidor) rmii.lookup("Contador");
                new Thread(new implementacionCliente(servidor, 0)).start();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}