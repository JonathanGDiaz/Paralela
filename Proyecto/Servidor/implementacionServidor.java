package Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import Contador.*;

/**
 * implementacionServidor
 */
public class implementacionServidor extends UnicastRemoteObject implements contadorServidor {
    public String arr, primerCliente, segundoCliente;
    private int contador;

    public implementacionServidor() throws RemoteException {
        this.arr = "";
        this.contador = 0;
        this.primerCliente = "";
        this.segundoCliente = "";
    }

    @Override
    public void registro(char[] cadena, int id) throws RemoteException {
        if (!primerCliente.equals("") && !segundoCliente.equals(""))
            arr = primerCliente.concat(segundoCliente);
        else {
            if (id == 0)
                primerCliente = new String(cadena);
            else if (id == 1)
                segundoCliente = new String(cadena);
        }
        System.out.println("Cadena creada");
    }

    @Override
    public int encuentroLineal(String caracter) throws RemoteException {
        contador = 0;
        Lineal l = new Lineal(caracter, arr);
        l.repartir(0, arr.length());
        contador = l.contador;
        return contador;
    }

    @Override
    public int encuentroFJ(String caracter) throws RemoteException {
        contador = 0;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        contador = pool.invoke(new ForkJoin(0, arr.length(), caracter, arr));
        pool.shutdown();
        return contador;
    }

    @Override
    public int encuentroExServ(String caracter) throws RemoteException, InterruptedException, ExecutionException {
        contador = 0;
        int procesors = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(procesors);
        Future<Integer> resultado = service.submit(new ExecutonerService(0, arr.length(), caracter, arr));
        contador = (int) resultado.get();
        service.shutdown();
        return contador;
    }

    @Override
    public void limpiar() throws RemoteException {
        primerCliente = "";
        segundoCliente = "";
        arr = "";
    }
}