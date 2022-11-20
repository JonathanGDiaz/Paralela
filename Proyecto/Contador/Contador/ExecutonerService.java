package Contador;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutonerService implements Callable<Integer> {
    String c, arr;
    int inicio, fin, tam, contador;
    ExecutorService serv;

    public ExecutonerService(int in, int fi, String caracter, String arreglo) {
        this.c = caracter;
        this.inicio = in;
        this.fin = fi;
        this.tam = fin - inicio;
        this.contador = 0;
        this.arr = arreglo;
    }

    @Override
    public Integer call() throws Exception {
        if (tam <= 1000000) {
            return encontrar(inicio, fin);
        }
        int mid = tam / 2;
        serv = Executors.newFixedThreadPool(2);
        Future<Integer> e1 = serv.submit(new ExecutonerService(inicio, inicio + mid, c, arr));
        Future<Integer> e2 = serv.submit(new ExecutonerService(inicio + mid, fin, c, arr));
        serv.shutdown();
        return (int) e1.get() + (int) e2.get();
    }

    private Integer encontrar(int ini, int fi) {
        for (int i = ini; i < fi; i++) {
            if (c.equals(Character.toString(arr.charAt(i))))
                contador++;
        }
        return contador;
    }
}
