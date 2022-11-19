import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Servidor
 */
public class servidorRMI {
    public static void main(String[] args) {
        try {
            // System.setProperty("java.rmi.server.hostname", "192.168.43.64");
            Registry rmi = LocateRegistry.createRegistry(1111);
            rmi.rebind("Chat", (Remote) new implementacionChat());
            System.out.println("Servidor activo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
