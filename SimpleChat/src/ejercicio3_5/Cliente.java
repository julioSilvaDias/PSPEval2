package ejercicio3_5;

import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conexión establecida con el servidor.");

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
