package chatSimple;

import java.io.DataInputStream;
import java.net.Socket;
import java.net.SocketException;

public class RecibirThread extends Thread{
	private Socket socket;
	
	public RecibirThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			String mensaje;
			
			while(true) {
				mensaje = entrada.readUTF();
				
				if(mensaje.equalsIgnoreCase("salir")) {
					System.out.println("El otro usuario ha finalizado la conversacion");
					socket.close();
					break;
				}
				
				System.out.println("\033[0;30m " + mensaje);
			}
		}catch(SocketException se) {
			System.out.println("Error!! " + se.getMessage());
		}catch(Exception e) {
			System.out.println("Error!! " + e.getMessage());
			e.printStackTrace();
		}
	}
}
