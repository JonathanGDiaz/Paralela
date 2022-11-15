import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

/**
 * clienteRMI
 */
public class clienteRMI {

    public static void main(String[] args) {
        try {
            String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
            String nom = nombre;
            // System.setProperty("java.rmi.server.hostname", "192.168.1.13");
            Registry rmii = LocateRegistry.getRegistry("192.168.122.230", 1111);
            chatServidor servidor = (chatServidor) rmii.lookup("Chat");
            new Thread(new implementacionChatCliente(nom, servidor)).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}