package Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Servidor.contadorServidor;

/**
 * implementacionCliente
 */
public class implementacionCliente extends UnicastRemoteObject implements Runnable {
    private int PANEL_HEIGHT = 650;
    private int PANEL_WIDTH = 500;
    private int id, contador;
    private contadorServidor servidor;
    private JFrame frame;
    private JTextField txtCaracter, txtTam;
    private JButton btnCrear, btnLineal, btnFJ, btnExServ, btnLimpiar;
    private JLabel lblEstado, lblTam, lblCaracter, lblTexto, lblTiempoLineal, lblTiempoFJ, lblTiempoExServ, lblNose;
    private JPanel panel;
    private JTextArea texto;
    private JScrollPane barra;
    private String cadenaRelleno;

    protected implementacionCliente(contadorServidor servidor, int id) throws RemoteException {
        this.id = id;
        this.contador = 0;
        this.servidor = servidor;
        this.cadenaRelleno = "abcdefghijklmnñopqrstuvwxyz0123456789 ";
    }

    @Override
    public void run() {
        creaFrame();
    }

    void creaFrame() {
        // Inicializamos los componentes
        frame = new JFrame("Cliente " + id);
        frame.setBounds(250, 80, PANEL_WIDTH, PANEL_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        frame.add(lblTexto);
        frame.add(panel);
        // TAMAÑO DEL ARREGLO
        lblTam = new JLabel("O el tamaño del arreglo aleatorio a generar");
        lblTam.setBounds(120, 260, 290, 25);
        txtTam = new JTextField("");
        txtTam.setBounds(150, 285, 100, 25);
        frame.add(lblTam);
        frame.add(txtTam);
        // CARACTER A BUSCAR
        lblCaracter = new JLabel("Caracter a buscar");
        lblCaracter.setBounds(200, 320, 150, 25);
        txtCaracter = new JTextField();
        txtCaracter.setBounds(200, 345, 100, 25);
        frame.add(lblCaracter);
        frame.add(txtCaracter);
        // BOTON PARA CREAR ARREGLO
        btnCrear = new JButton("Crear");
        btnCrear.setBounds(260, 285, 100, 25);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (txtTam.getText().toString().equals("") && !texto.getText().toString().equals("")) {
                        char[] tempArr = texto.getText().toString().toCharArray();
                        servidor.registro(tempArr, id);
                    } else {
                        int tam = Integer.parseInt(txtTam.getText());
                        // if (tam >= 10000 && tam < 100000)
                        // tam = tam * 1000;
                        Random random = new Random();
                        int temp = 0;
                        char[] tempArr = new char[tam];
                        for (int i = 0; i < tam; i++) {
                            temp = random.nextInt(cadenaRelleno.length());
                            tempArr[i] = cadenaRelleno.charAt(temp);
                        }
                        servidor.registro(tempArr, id);
                    }
                    System.out.println("Cadena enviada");
                } catch (Exception e) {
                }
            }
        });
        frame.add(btnCrear);
        // BOTON PARA LIMPIAR
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(370, 285, 100, 25);
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    servidor.limpiar();
                } catch (Exception e) {
                }
            }
        });
        frame.add(btnLimpiar);
        // BOTON PARA LINEAL
        btnLineal = new JButton("Lineal");
        btnLineal.setBounds(90, 380, 100, 24);
        btnLineal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    double inicio = System.nanoTime();
                    contador = servidor.encuentroLineal(txtCaracter.getText().toString());
                    double fin = System.nanoTime();
                    double tiempo = (double) (fin - inicio) / Math.pow(10, 6);
                    lblTiempoLineal.setText("Tiempo lineal: " + tiempo + " milisegundos");
                    lblEstado.setText("Veces encontrado: " + Integer.toString(contador));
                } catch (Exception e) {
                }
            }
        });
        frame.add(btnLineal);
        // BOTON PARA FORKJOIN
        btnFJ = new JButton("Forkjoin");
        btnFJ.setBounds(200, 380, 100, 24);
        btnFJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    double inicio = System.nanoTime();
                    contador = servidor.encuentroFJ(txtCaracter.getText().toString());
                    double fin = System.nanoTime();
                    double tiempo = (double) (fin - inicio) / Math.pow(10, 6);
                    lblTiempoFJ.setText("Tiempo lineal: " + tiempo + " milisegundos");
                    lblEstado.setText("Veces encontrado: " + Integer.toString(contador));
                } catch (Exception e) {
                }
            }
        });
        frame.add(btnFJ);
        // BOTON PARA EXECUTOR SERVICE
        btnExServ = new JButton("ExServ");
        btnExServ.setBounds(310, 380, 100, 24);
        btnExServ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    double inicio = System.nanoTime();
                    contador = servidor.encuentroExServ(txtCaracter.getText().toString());
                    double fin = System.nanoTime();
                    double tiempo = (double) (fin - inicio) / Math.pow(10, 6);
                    lblTiempoExServ.setText("Tiempo lineal: " + tiempo + " milisegundos");
                    lblEstado.setText("Veces encontrado: " + Integer.toString(contador));
                } catch (Exception e) {
                }
            }
        });
        frame.add(btnExServ);
        // ESTADO
        lblEstado = new JLabel("Veces encontrado: ");
        lblEstado.setBounds(180, 414, 250, 25);
        frame.add(lblEstado);
        // TIEMPO TARDADO LINEAL
        lblTiempoLineal = new JLabel("Tiempo lineal: ");
        lblTiempoLineal.setBounds(20, 440, 350, 25);
        frame.add(lblTiempoLineal);
        // TIEMPO TARDADO FORKJOIN
        lblTiempoFJ = new JLabel("Tiempo Forkjoin: ");
        lblTiempoFJ.setBounds(20, 475, 350, 25);
        frame.add(lblTiempoFJ);
        // TIEMPO TARDADO EXECUTOR SERVICE
        lblTiempoExServ = new JLabel("Tiempo ExServ: ");
        lblTiempoExServ.setBounds(20, 510, 350, 25);
        frame.add(lblTiempoExServ);
        lblNose = new JLabel(" ");
        lblNose.setBounds(20, 545, 350, 25);
        frame.add(lblNose);
        // VOLVEMOS EL FRAME VISIBLE
        frame.setVisible(true);
    }
}