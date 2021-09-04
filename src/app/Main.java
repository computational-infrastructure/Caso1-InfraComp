package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {
	static String pathToProperties = "config.properties";
	static int cantidadPlatos = 0;
	static int numComensales = 0;
	static int numCubiertosT1 = 0;
	static int numCubiertosT2 = 0;
	static int tamFregadero = 0;
	static boolean carga = false;

	public static void main(String[] args) {
		cargarDatos();
		// Creación de los threads
		Main.crearLogs("___________________________________________________________________");
		Main.crearLogs("Ejecución de hora: " + LocalDateTime.now());
		CyclicBarrier barrera = new CyclicBarrier(numComensales);
		CyclicBarrier barrreraFinal = new CyclicBarrier(numComensales);
		Mesa mesa = new Mesa(numCubiertosT1, numCubiertosT2);
		new Fregadero(tamFregadero);
		Lavaplatos lavaplatos = new Lavaplatos(mesa);

		for (int i = 0; i < numComensales; i++) {
			new Comensal(cantidadPlatos, barrera, mesa, i, barrreraFinal).start();
		}
		lavaplatos.start();
	}

	public static void crearLogs(String mensaje) {
		System.out.println(mensaje);
		File logs = new File("./logs/logs.txt");
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logs, true)), true);
			pw.println(mensaje);
			pw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void cargarDatos() {
		Scanner sc = new Scanner(System.in);
		while (!carga) {
			// Carga de datos del archivo Properties
			try {
				InputStream input = new FileInputStream(pathToProperties);
				Properties prop = new Properties();
				prop.load(input);

				cantidadPlatos = Integer.parseInt(prop.getProperty("concurrencia.numPlatos"));
				numComensales = Integer.parseInt(prop.getProperty("concurrencia.numComensales"));
				numCubiertosT1 = Integer.parseInt(prop.getProperty("concurrencia.numCubiertosT1"));
				numCubiertosT2 = Integer.parseInt(prop.getProperty("concurrencia.numCubiertosT2"));
				tamFregadero = Integer.parseInt(prop.getProperty("concurrencia.tamFregadero"));
				System.out.println("Configuración cargada");
				carga = true;
				sc.close();
			} catch (FileNotFoundException ex) {
				System.out.println("No se encontró el archivo.");
				System.out.println("Por favor ingresa la ruta del archivo de propiedades:");
				pathToProperties = sc.nextLine();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
