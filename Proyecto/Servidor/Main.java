package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "192.168.43.64");
            Registry rmi = LocateRegistry.createRegistry(1111);
            rmi.rebind("Contador", (Remote) new implementacionServidor());
            System.out.println("Changarro abierto");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}