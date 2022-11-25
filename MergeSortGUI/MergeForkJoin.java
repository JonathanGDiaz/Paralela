import java.util.concurrent.RecursiveAction;

// JONATHAN GUILLERMO DIAZ MAGALLANES 
/**
 * MergeForkJoin
 */
public class MergeForkJoin extends RecursiveAction{
  public int[] arr;

  public MergeForkJoin(){
    this.arr = Main.arr1;
  }
  public MergeForkJoin(int[] arr){
    this.arr = arr;
  }
  @Override
  protected void compute() {
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
    invokeAll(new MergeForkJoin(arrIzquierda), new MergeForkJoin(arrDerecha));    
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
