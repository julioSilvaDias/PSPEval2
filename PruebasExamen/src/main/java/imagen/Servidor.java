package imagen;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		int puerto = 5000;
		try {
			ServerSocket ss = new ServerSocket(puerto);
			System.out.println("Esperando conexion del cliente");
			Socket socket = ss.accept();
			System.out.println("Cliente conectado");
			
			File archivoImagen = new File("imagen.jpg");
			FileInputStream fis = new FileInputStream(archivoImagen);
			OutputStream ops = socket.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesRead;
			
			while((bytesRead = fis.read(buffer)) != -1) {
				ops.write(buffer, 0, bytesRead);
			}
			
			ops.flush();
			System.out.println("Imagen Enviada correctamente.");
			
			fis.close();
			socket.close();
			
		}catch(Exception e) {
			
		}
	}
}
