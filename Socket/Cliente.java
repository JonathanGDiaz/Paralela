import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Cliente
 */
public class Cliente {
    public static final int PUERTO = 1111;
    public static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        System.out.println("Host " + HOST);
        Socket socket = new Socket(HOST, PUERTO);
        Scanner in = new Scanner(System.in);
        try {
            PrintWriter salida = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            while (true) {
                String str = in.nextLine();
                salida.println("Usuario: soy tu cliente " + str);
                System.out.println("Mensaje enviado al server");
                if (str.equals("FIN")) {
                    salida.println(str);
                    break;
                }
            }
        } finally {
            in.close();
            System.out.println("Cerrando el changarro");
            socket.close();
        }
    }

}