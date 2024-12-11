package ejercicio3_5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecibirThread extends Thread {
    private Socket socket;

    public RecibirThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            boolean encendido = true;
            DataInputStream entrada = socket.getInputStream();
            String mensaje;

            while (encendido) {
                mensaje = entrada.read() + "";
                if (mensaje == null || mensaje.equalsIgnoreCase("salir")) {
                    encendido = false;
                    if (!socket.isClosed()) {
                        socket.close();
                        System.out.println("se acabo la fiesta" );
                    }
                    break;
                } else {
                    System.out.println("\033[0;32m" + mensaje );
                }
            }
        } catch (Exception e) {
            if (!socket.isClosed()) {
                e.printStackTrace();
            }
        }
    }
}
