package ejercicio3_5;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EnviarThread extends Thread {
    private Socket socket;

    public EnviarThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            boolean encendido = true;
            Scanner teclado = new Scanner(System.in);
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            String mensaje;

            while (encendido) {
                mensaje = teclado.nextLine();
                salida.writeUTF(mensaje);
                salida.flush();

                if (mensaje.equalsIgnoreCase("salir")) {
                    encendido = false;
                    if (!socket.isClosed()) {
                        socket.close();
                    }
                }
            }
            teclado.close();
        } catch (Exception e) {
            if (!socket.isClosed()) {
                e.printStackTrace();
            }
        }
    }
}
