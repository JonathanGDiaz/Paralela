package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * calculadoraServidor
 */
public interface calculadoraServidor extends Remote {
    void registro(double cliente, int id) throws RemoteException;

    double numero(int id) throws RemoteException;

}