package ejercicio3_3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

	public static void main(String[] args) {
		int puerto = 5000;

		try {
			ServerSocket serverSocket = new ServerSocket(puerto);
			Socket socket = serverSocket.accept();
			
			Persona p = new Persona();
			
			p.setNif("43728931S");
			p.setApellido("Alonso");
			p.setNombre("Diego");
			SimpleDateFormat sdt= new SimpleDateFormat("YYYY-MM-DD");
			Date date = sdt.parse("2008-02-03");
			p.setFechaNascimiento(date);
			p.setGenero('M');
			
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			salida.writeObject(p);
			salida.flush();
			
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			System.out.println(entrada.readObject());
			

		} catch (Exception e) {
			
		}
	}
}