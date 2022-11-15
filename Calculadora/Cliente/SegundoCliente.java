package Cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Servidor.calculadoraServidor;

/**
 * Main
 */
public class SegundoCliente {
    public static void main(String[] args) {
        try {
            Registry rmii = LocateRegistry.getRegistry("localhost", 1111);
            calculadoraServidor servidor = (calculadoraServidor) rmii.lookup("Calculadora");
            new Thread(new implementacionCliente(servidor, 1)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}