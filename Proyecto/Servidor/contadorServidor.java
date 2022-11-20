package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

/**
 * contadorServidor
 */
public interface contadorServidor extends Remote {
    // Manejo de variables del servidor
    void registro(char[] cadena, int id) throws RemoteException;

    void limpiar() throws RemoteException;

    // Cada uno va a regresar el contador
    int encuentroLineal(String caracter) throws RemoteException;

    int encuentroFJ(String caracter) throws RemoteException;

    int encuentroExServ(String caracter) throws RemoteException, InterruptedException, ExecutionException;
}