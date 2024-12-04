package ejercicio3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int puerto = 5000;
		int clientesAtendidos = 0;
		int numMaxConexiones = 3;

		try {
			ServerSocket serverSocket = new ServerSocket(puerto);
			System.out.println("Servidor iniciado, esperando conexiones...");

			while (clientesAtendidos < numMaxConexiones) {
				Socket socket = serverSocket.accept();
				clientesAtendidos++;
				System.out.println("Cliente " + clientesAtendidos + " conectado");

				InputStream entrada = socket.getInputStream();
				BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
				String mensajeCliente = lector.readLine();
				System.out.println("Recibido: " + mensajeCliente);

				OutputStream salida = socket.getOutputStream();
				String mensajeSalida = "Saludos desde el servidor al cliente no: " + clientesAtendidos + "\n";
				salida.write(mensajeSalida.getBytes());

				socket.close();
			}

		} catch (IOException e) {
			System.err.println("Error en el servidor: " + e.getMessage());
		}
	}
}
