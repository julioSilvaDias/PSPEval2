package ejercicio3_3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 5000;

		try {
			Socket socket = new Socket(host, puerto);
			System.out.println("Comunicacion estabelecida");
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			
			Persona p = (Persona) entrada.readObject();
			p.setNombre("Fernando");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date date = sdf.parse("1990-02-01");
			p.setFechaNascimiento(date);
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(p);
			oos.flush();

		} catch (Exception e) {
		
		}
	}
}
