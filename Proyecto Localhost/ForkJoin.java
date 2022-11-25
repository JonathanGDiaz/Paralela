import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Integer> {
    String c;
    int inicio, fin, tam, contador;

    public ForkJoin(int in, int fi, String caracter) {
        this.c = caracter;
        this.inicio = in;
        this.fin = fi;
        this.tam = fin - inicio;
        this.contador = 0;
    }

    @Override
    protected Integer compute() {
        if (tam <= 1000000) {
            return encontrar(inicio, fin);
        }
        int mid = tam / 2;
        ForkJoin f1 = new ForkJoin(inicio, inicio + mid, c);
        ForkJoin f2 = new ForkJoin(inicio + mid, fin, c);
        invokeAll(f1, f2);
        return f1.join() + f2.join();
    }

    private Integer encontrar(int ini, int fi) {
        for (int i = ini; i < fi; i++) {
            if (c.equals(Character.toString(Main.arr.charAt(i))))
                contador++;
        }
        return contador;
    }

}
