package imagen;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 5000;
		
		try {
			
			Socket socket = new Socket(host, puerto);
			System.out.println("conectado al servidor. ");
			
			InputStream is = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream("imagen_recibida.jpg");
			
			byte[] buffer = new byte[4096];
			int bytesRead;
			
			while((bytesRead = is.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}
			
			System.out.println("Imagen recibida correctamente.");
			fos.close();
		}catch(Exception e) {
			
		}
	}
}
