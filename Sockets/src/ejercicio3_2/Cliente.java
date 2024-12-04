package ejercicio3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 5000;

		try (Socket socket = new Socket(host, puerto)) {
			System.out.println("Conexión realizada con el servidor");

			OutputStream salida = socket.getOutputStream();
			String mensajeSalida = "Hola servidor, soy un cliente\n";
			salida.write(mensajeSalida.getBytes());

			InputStream entrada = socket.getInputStream();
			BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
			String mensajeServidor = lector.readLine();
			System.out.println(mensajeServidor);

		} catch (IOException e) {
			System.out.println("Demasiados clientes por hoy");
		}
	}
}
