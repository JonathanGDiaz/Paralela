package Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * implementacionServidor
 */
public class implementacionServidor extends UnicastRemoteObject implements calculadoraServidor {
    public double[] clientes;

    protected implementacionServidor() throws RemoteException {
        this.clientes = new double[2];
    }

    @Override
    public void registro(double cliente, int id) throws RemoteException {
        clientes[id] = cliente;
        // System.out.println(clientes[id]);
    }

    @Override
    public double numero(int id) throws RemoteException {
        // System.out.println(clientes[id]);
        return clientes[id];
    }

}