import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

public class MergeExecutonerService implements Runnable {
  public ExecutorService service;
  public ExecutorService service1;

  public int[] arr;

  public MergeExecutonerService(int[] arr) {
    this.arr = arr;
    // service = Executors.newSingleThreadExecutor();
    // service1 = Executors.newSingleThreadExecutor();
  }

  public MergeExecutonerService() {
    this.arr = Main.arr;
    // service = Executors.newSingleThreadExecutor();
    // service1 = Executors.newSingleThreadExecutor();
  }

  @Override
  public void run() {
    double inicio = System.nanoTime();
    mergeSort(arr);
    double fin = System.nanoTime();
    Main.tiempo = (double) (fin - inicio) / Math.pow(10, 6);
    Main.labelTiempoExServ.setText("ExServ: " + Double.toString(Main.tiempo) + " milisegundos");
  }

  private static void mergeSort(int[] arr) {
    int tam = arr.length;
    // Si solo queda un elemento se termina
    if (tam <= 1)
      return;
    // Obtenemos valor medio para cortar el array
    int mid = tam / 2;
    // Declaramos dos sub arreglos
    int[] arrIzquierda = new int[mid];
    int[] arrDerecha = new int[tam - mid];
    // Declaramos los indices de los arreglos
    int indexDerecha = 0, indexIzquierda = 0;
    // Añadimos los valores a los nuevos arreglos
    for (; indexIzquierda < tam; indexIzquierda++) {
      if (indexIzquierda < mid)
        arrIzquierda[indexIzquierda] = arr[indexIzquierda];
      else {
        arrDerecha[indexDerecha] = arr[indexIzquierda];
        indexDerecha++;
      }
    }
    // Empezamos la recursion
    mergeSort(arrIzquierda);
    mergeSort(arrDerecha);
    // Volvemos a juntar los arreglos
    merge(arrIzquierda, arrDerecha, arr);
  }

  private static void merge(int[] arrIzquierda, int[] arrDerecha, int[] arr) {
    // Obtenemos tamaños de los arreglos
    int tamIzquierda = arr.length / 2;
    int tamDerecha = arr.length - tamIzquierda;
    // Declaramos los indices
    int i = 0, indexIzquierda = 0, indexDerecha = 0;
    // Empezamos con la asignacion de valores
    while (indexIzquierda < tamIzquierda && indexDerecha < tamDerecha) {
      // Comparamos los valores
      if (arrIzquierda[indexIzquierda] < arrDerecha[indexDerecha]) {
        arr[i] = arrIzquierda[indexIzquierda];
        i++;
        indexIzquierda++;
      } else {
        arr[i] = arrDerecha[indexDerecha];
        i++;
        indexDerecha++;
      }
    }
    // Añadimos los valores que pueden llegar a sobrar
    while (indexIzquierda < tamIzquierda) {
      arr[i] = arrIzquierda[indexIzquierda];
      i++;
      indexIzquierda++;
    }
    while (indexDerecha < tamDerecha) {
      arr[i] = arrDerecha[indexDerecha];
      i++;
      indexDerecha++;
    }
  }
}
