package Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Servidor.calculadoraServidor;

/**
 * implementacionCliente
 */
public class implementacionCliente extends UnicastRemoteObject implements calculadoraCliente, Runnable {
    public calculadoraServidor servidor;
    public int id;

    protected implementacionCliente(calculadoraServidor servidor, int id) throws RemoteException {
        this.servidor = servidor;
        this.id = id;
    }

    @Override
    public void run() {
        creaFrame();
    }

    @Override
    public double numeroCliente(int id) throws RemoteException {
        return 0;
    }

    void creaFrame() {
        JFrame frame = new JFrame("Calculadora del cliente");
        JPanel panel = new JPanel();

        // Caracteristicas del frame
        frame.setBounds(500, 500, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(5, 5));

        // Caracteristicas del panel
        GridLayout g = new GridLayout(3, 3);
        g.setHgap(5);
        g.setVgap(5);
        panel.setLayout(g);

        // Componentes
        JButton btnSuma, btnResta, btnDivision, btnMultiplicacion, btnAdd;
        JTextField txtNumero, txtResultado;

        // Texto de numero
        txtNumero = new JTextField();
        txtNumero.setFont(new Font("Serif", Font.PLAIN, 30));

        // Texto de resultado
        txtResultado = new JTextField();
        txtResultado.setFont(new Font("Serif", Font.PLAIN, 30));

        // Boton añadir
        btnAdd = new JButton("Añadir");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                double numero = Double.parseDouble(txtNumero.getText().toString());
                try {
                    servidor.registro(numero, id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        // Boton de suma
        btnSuma = new JButton("Suma");
        btnSuma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int idServidor = 0;
                double numeroServidor = 0, numeroCliente = 0;
                try {
                    if (id == 0)
                        idServidor = 1;
                    else if (id == 1)
                        idServidor = 0;
                    // Obtenemos los numeros del servidor
                    numeroServidor = servidor.numero(idServidor);
                    numeroCliente = servidor.numero(id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                txtResultado.setText(Double.toString(numeroCliente + numeroServidor));
            }
        });

        // Boton de resta
        btnResta = new JButton("Resta");
        btnResta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int idServidor = 0;
                double numeroServidor = 0, numeroCliente = 0;
                try {
                    if (id == 0)
                        idServidor = 1;
                    else if (id == 1)
                        idServidor = 0;
                    // Obtenemos los numeros del servidor
                    numeroServidor = servidor.numero(idServidor);
                    numeroCliente = servidor.numero(id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                txtResultado.setText(Double.toString(numeroCliente - numeroServidor));
            }
        });

        // Boton de division
        btnDivision = new JButton("Division");
        btnDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int idServidor = 0;
                double numeroServidor = 0, numeroCliente = 0;
                try {
                    if (id == 0)
                        idServidor = 1;
                    else if (id == 1)
                        idServidor = 0;
                    // Obtenemos los numeros del servidor
                    numeroServidor = servidor.numero(idServidor);
                    numeroCliente = servidor.numero(id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                txtResultado.setText(Double.toString(numeroCliente / numeroServidor));
            }
        });

        // Boton de multiplicacion
        btnMultiplicacion = new JButton("Multiplicacion");
        btnMultiplicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int idServidor = 0;
                double numeroServidor = 0, numeroCliente = 0;
                try {
                    if (id == 0)
                        idServidor = 1;
                    else if (id == 1)
                        idServidor = 0;
                    // Obtenemos los numeros del servidor
                    numeroServidor = servidor.numero(idServidor);
                    numeroCliente = servidor.numero(id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                txtResultado.setText(Double.toString(numeroCliente * numeroServidor));
            }
        });

        // Añadimos los componentes al panel
        panel.add(btnSuma);
        panel.add(btnResta);
        panel.add(btnDivision);
        panel.add(btnMultiplicacion);
        panel.add(btnAdd);
        frame.add(txtNumero, BorderLayout.NORTH);
        frame.add(txtResultado, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}