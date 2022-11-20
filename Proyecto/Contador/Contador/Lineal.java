package Contador;

public class Lineal {
    String c, arr;
    public int contador;

    public Lineal(String caracter, String arreglo) {
        this.c = caracter;
        this.contador = 0;
        this.arr = arreglo;
    }

    public void repartir(int inicio, int fin) {
        int tam = fin - inicio;
        if (tam <= 100000) {
            encontrar(inicio, fin);
            return;
        }
        int mid = tam / 2;
        repartir(inicio, inicio + mid);
        repartir(inicio + mid, fin);
        return;
    }

    private int encontrar(int inicio, int fin) {
        for (int i = inicio; i < fin; i++) {
            if (c.equals(Character.toString(arr.charAt(i))))
                contador++;
        }
        return contador;
    }
}
