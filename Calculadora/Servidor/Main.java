package Servidor;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        try {
            Registry rmi = LocateRegistry.createRegistry(1111);
            rmi.rebind("Calculadora", (Remote) new implementacionServidor());
            System.out.println("Servidor activo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}