import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// JONATHAN GUILLERMO DIAZ MAGALLANES

/**
 * Main
 */
public class Main extends JFrame implements ActionListener {
  private int PANEL_HEIGHT = 550;
  private int PANEL_WIDTH = 500;
  static JTextField txtCaracter, txtTam;
  static JButton btnCrear, btnLineal, btnFJ, btnExServ;
  static JLabel lblEstado, lblTam, lblCaracter, lblTexto, lblTiempoLineal, lblTiempoFJ, lblTiempoExServ;
  static JPanel panel;
  static JTextArea texto;
  static JScrollPane barra;
  static String arr;
  static double tiempo;
  static int contador;
  static ExecutorService service;
  public String cadenaRelleno;

  Main() {
    contador = 0;
    // CADENA DE RELLENO
    cadenaRelleno = "abcdefghijklmnñopqrstuvwxyz0123456789 ";
    arr = "";
    // VALORES DEL FRAME
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    this.setLayout(null);
    this.setTitle("Proyecto");
    this.setLocationRelativeTo(null);
    // PANEL DEL TEXTAREA Y SU RESPECTIVA SCROLLBAR
    lblTexto = new JLabel("Ingrese el texto");
    lblTexto.setBounds(200, 25, 200, 25);
    panel = new JPanel();
    panel.setBounds(50, 50, 400, 200);
    panel.setLayout(new BorderLayout());
    texto = new JTextArea("");
    texto.setLineWrap(true);
    barra = new JScrollPane(texto);
    barra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    panel.add(barra, BorderLayout.CENTER);
    this.add(lblTexto);
    this.add(panel);
    // TAMAÑO DEL ARREGLO
    lblTam = new JLabel("O el tamaño del arreglo aleatorio a generar");
    lblTam.setBounds(120, 260, 290, 25);
    txtTam = new JTextField("");
    txtTam.setBounds(150, 285, 100, 25);
    this.add(lblTam);
    this.add(txtTam);
    // CARACTER A BUSCAR
    lblCaracter = new JLabel("Caracter a buscar");
    lblCaracter.setBounds(200, 320, 150, 25);
    txtCaracter = new JTextField();
    txtCaracter.setBounds(200, 345, 100, 25);
    this.add(lblCaracter);
    this.add(txtCaracter);
    // BOTON PARA CREAR ARREGLO
    btnCrear = new JButton("Crear");
    btnCrear.setBounds(260, 285, 100, 25);
    btnCrear.addActionListener(this);
    this.add(btnCrear);
    // BOTON PARA LINEAL
    btnLineal = new JButton("Lineal");
    btnLineal.setBounds(90, 380, 100, 24);
    btnLineal.addActionListener(this);
    this.add(btnLineal);
    // BOTON PARA FORKJOIN
    btnFJ = new JButton("Forkjoin");
    btnFJ.setBounds(200, 380, 100, 24);
    btnFJ.addActionListener(this);
    this.add(btnFJ);
    // BOTON PARA EXECUTOR SERVICE
    btnExServ = new JButton("ExServ");
    btnExServ.setBounds(310, 380, 100, 24);
    btnExServ.addActionListener(this);
    this.add(btnExServ);
    // ESTADO
    lblEstado = new JLabel("Veces encontrado: ");
    lblEstado.setBounds(180, 414, 150, 25);
    this.add(lblEstado);
    // TIEMPO TARDADO LINEAL
    lblTiempoLineal = new JLabel("Tiempo lineal: ");
    lblTiempoLineal.setBounds(20, 440, 350, 25);
    this.add(lblTiempoLineal);
    // TIEMPO TARDADO FORKJOIN
    lblTiempoFJ = new JLabel("Tiempo Forkjoin: ");
    lblTiempoFJ.setBounds(20, 475, 350, 25);
    this.add(lblTiempoFJ);
    // TIEMPO TARDADO EXECUTOR SERVICE
    lblTiempoExServ = new JLabel("Tiempo ExServ: ");
    lblTiempoExServ.setBounds(20, 510, 350, 25);
    this.add(lblTiempoExServ);
    // VOLVEMOS EL FRAME VISIBLE
    this.setVisible(true);
  }

  public static void main(String[] args) {
    new Main();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnCrear)
      crearArreglo();
    else if (e.getSource() == btnLineal)
      encuentroLineal();
    else if (e.getSource() == btnFJ)
      encuentroFJ();
    else if (e.getSource() == btnExServ) {
      try {
        encuentroExServ();
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      } catch (ExecutionException e1) {
        e1.printStackTrace();
      }
    }
  }

  private void crearArreglo() {
    if (txtTam.getText().toString().equals("") && !texto.getText().toString().equals(""))
      arr = texto.getText().toString();
    else {
      int tam = Integer.parseInt(txtTam.getText());
      if (tam >= 10000 && tam < 100000)
        tam = tam * 1000;
      Random random = new Random();
      int temp = 0;
      char[] tempArr = new char[tam];
      for (int i = 0; i < tam; i++) {
        temp = random.nextInt(cadenaRelleno.length());
        tempArr[i] = cadenaRelleno.charAt(temp);
      }
      arr = new String(tempArr);
    }
    System.out.println("Cadena creada");
  }

  private void encuentroLineal() {
    contador = 0;
    double inicio = System.nanoTime();
    Lineal l = new Lineal(txtCaracter.getText());
    l.repartir(0, Main.arr.length());
    contador = l.contador;
    double fin = System.nanoTime();
    tiempo = (double) (fin - inicio) / Math.pow(10, 6);
    lblTiempoLineal.setText("Tiempo lineal: " + tiempo + " milisegundos");
    lblEstado.setText("Veces encontrado: " + Integer.toString(contador));
  }

  private void encuentroFJ() {
    contador = 0;
    ForkJoinPool pool = ForkJoinPool.commonPool();
    double inicio = System.nanoTime();
    contador = pool.invoke(new ForkJoin(0, arr.length(), txtCaracter.getText()));
    double fin = System.nanoTime();
    tiempo = (double) (fin - inicio) / Math.pow(10, 6);
    lblTiempoFJ.setText("Tiempo ForkJoin: " + tiempo + " milisegundos");
    lblEstado.setText("Veces encontrado: " + Integer.toString(contador));
    pool.shutdown();
  }

  private void encuentroExServ() throws InterruptedException, ExecutionException {
    contador = 0;
    int procesors = Runtime.getRuntime().availableProcessors();
    service = Executors.newFixedThreadPool(procesors);
    double inicio = System.nanoTime();
    Future resultado = service.submit(new ExecutonerService(0, arr.length(), txtCaracter.getText()));
    contador = (int) resultado.get();
    double fin = System.nanoTime();
    tiempo = (double) (fin - inicio) / Math.pow(10, 6);
    lblTiempoExServ.setText("Tiempo ExServ: " + tiempo + " milisegundos");
    lblEstado.setText("Veces encontrado: " + Integer.toString(contador));
    service.shutdown();
  }
}
