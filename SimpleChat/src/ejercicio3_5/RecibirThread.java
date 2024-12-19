package ejercicio3_5;

import java.io.DataInputStream;
import java.net.Socket;

public class RecibirThread extends Thread {
	private Socket socket;

	public RecibirThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			String mensaje;
			while (true) {
				mensaje = entrada.readUTF();
				if (mensaje.equalsIgnoreCase("salir")) {
					System.out.println("El otro usuario ha finalizado la conexión.");
					socket.close();
					break;
				}
				System.out.println("\033[0;30m " + mensaje);
			}
		} catch (java.net.SocketException e) {
			System.out.println("Conexión cerrada.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
