
import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Main
 */
public class Main extends JFrame implements ActionListener {
  final int PANEL_WIDTH = 730;
  final int PANEL_HEIGHT = 375;
  static JTextField texto;
  static JButton crear, lineal, fj, exServ;
  static JTextArea label;
  static JLabel labelTiempoLineal, labelTiempoFJ, labelTiempoExServ;
  static int[] arr, arr1, arr2;
  static double tiempo = 0;
  static ExecutorService service;

  Main() {

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.setLayout(null);
    this.setTitle("Algoritmo MergeSort");
    this.setLocationRelativeTo(null);
    Border border = BorderFactory.createLineBorder(Color.black, 3);
    // VALORES PARA EL TEXTFIELD DEL TAMAÑO DEL ARREGLO
    texto = new JTextField();
    texto.setBounds(10, 25, 150, 25);
    this.add(texto);
    // VALORES DEL BOTON PARA CREAR EL ARREGLO
    crear = new JButton("Crear");
    crear.setBounds(170, 25, 75, 25);
    crear.addActionListener(this);
    this.add(crear);
    // VALORES DEL BOTON PARA ORDENAR EL ARREGLO LINEAL
    lineal = new JButton("Lineal");
    lineal.setBounds(255, 25, 100, 25);
    lineal.addActionListener(this);
    this.add(lineal);
    // VALORES DEL BOTON PARA ORDENAR EL ARREGLO FORKJOIN
    fj = new JButton("ForkJoin");
    fj.setBounds(365, 25, 100, 25);
    fj.addActionListener(this);
    this.add(fj);
    // VALORES DEL BOTON PARA ORDENAR EL ARREGLO EXECUTONER SERVICE
    exServ = new JButton("ExServ");
    exServ.setBounds(475, 25, 100, 25);
    exServ.addActionListener(this);
    this.add(exServ);
    // VALORES DE LA ETIQUETA PARA EL ARREGLO
    label = new JTextArea();
    label.setWrapStyleWord(true);
    label.setLineWrap(true);
    label.setBounds(10, 60, 700, 200);
    label.setBorder(border);
    this.add(label);
    // VALORES DE LA ETIQUETA PARA EL TIEMPO
    labelTiempoLineal = new JLabel("Lineal: ");
    labelTiempoLineal.setBounds(10, 280, 295, 25);
    this.add(labelTiempoLineal);
    // VALORES DE LA ETIQUETA PARA EL TIEMPO
    labelTiempoFJ = new JLabel("ForkJoin: ");
    labelTiempoFJ.setBounds(10, 315, 295, 25);
    this.add(labelTiempoFJ);
    // VALORES DE LA ETIQUETA PARA EL TIEMPO
    labelTiempoExServ = new JLabel("ExServ: ");
    labelTiempoExServ.setBounds(10, 340, 295, 25);
    this.add(labelTiempoExServ);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == crear)
      crearArreglo();
    else if (e.getSource() == lineal) {
      double inicio = System.nanoTime();
      ordenarArregloLineal();
      double fin = System.nanoTime();
      tiempo = (double) (fin - inicio) / Math.pow(10, 6);
      labelTiempoLineal.setText("Lineal: " + Double.toString(tiempo) + " milisegundos");
      imprimirArreglo();
    } else if (e.getSource() == fj) {
      double inicio = System.nanoTime();
      ordenarArregloForkJoin();
      double fin = System.nanoTime();
      tiempo = (double) (fin - inicio) / Math.pow(10, 6);
      labelTiempoFJ.setText("ForkJoin: " + Double.toString(tiempo) + " milisegundos");
      imprimirArreglo();
    } else if (e.getSource() == exServ) {
      ordenarArregloExecutonerService();
      imprimirArreglo();
    }
  }

  // --------------------------Metodos de botones-----------------------
  public void crearArreglo() {
    int tam = Integer.parseInt(texto.getText());
    if (tam <= 50000)
      tam = tam * 20;
    Random random = new Random();
    arr = new int[tam];
    // Rellenamos el arreglod e valores aleatorios
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(98 + 1);
    }
    arr1 = arr;
    arr2 = arr;
    label.setText("Arreglo creado");
    imprimirArreglo();
  }

  public void ordenarArregloLineal() {
    new MergeLineal();
  }

  public void ordenarArregloForkJoin() {
    ForkJoinPool pool = ForkJoinPool.commonPool();
    pool.invoke(new MergeForkJoin());
    pool.shutdown();
  }

  public void ordenarArregloExecutonerService() {
    service = Executors.newSingleThreadExecutor();
    service.execute(new MergeExecutonerService());
    service.shutdown();
  }

  public void imprimirArreglo() {
    // String temp = "";
    // label.setText(temp);
    // for (int i = 0; i < arr.length; i++) {
    // temp = label.getText();
    // temp = temp + " " + Integer.toString(arr[i]);
    // label.setText(temp);
    // }
    System.out.println("Arreglo creado");
  }
}
