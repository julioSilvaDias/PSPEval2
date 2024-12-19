package ejercicio3_5;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviarThread extends Thread {
	private Socket socket;

	public EnviarThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Scanner teclado = new Scanner(System.in);
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			String mensaje;
			while (true) {
				mensaje = teclado.nextLine();
				System.out.println("\033[0;32m" + mensaje);
				salida.writeUTF(mensaje);
				salida.flush();
				if (mensaje.equalsIgnoreCase("salir")) {
					socket.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
