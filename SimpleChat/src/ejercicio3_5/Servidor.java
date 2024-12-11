package ejercicio3_5;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Esperando conexión...");
            Socket socket = ss.accept();
            System.out.println("Se ha conectado " + socket.getInetAddress());

            EnviarThread et = new EnviarThread(socket);
            RecibirThread rt = new RecibirThread(socket);

            et.start();
            rt.start();

            et.join();
            rt.join();

            System.out.println("Chat finalizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
