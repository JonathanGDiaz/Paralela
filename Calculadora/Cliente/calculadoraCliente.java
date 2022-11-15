package Cliente;

import java.rmi.RemoteException;

/**
 * calculadoraCliente
 */
public interface calculadoraCliente {

    double numeroCliente(int id) throws RemoteException;
}