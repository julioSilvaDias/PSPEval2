package chatSimple;

import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		String host = "localhost";
		int port = 5000;
		
		try {
			Socket socket = new Socket(host, port);
			System.out.println("Conexion estabelecida con el servidor");
			
			EnviarThread et = new EnviarThread(socket);
			RecibirThread re = new RecibirThread(socket);
			
			et.start();
			re.start();
			
			et.join();
			re.join();
			
			System.out.println("chat finalizado... ");
		}catch(Exception e) {
			
		}
	}
}
