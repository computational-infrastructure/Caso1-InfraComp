package app;

import java.util.Properties;
import java.util.concurrent.CyclicBarrier;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		CyclicBarrier barrera = new CyclicBarrier(numComensales);
		Mesa mesa = new Mesa();
		Fregadero fregadero = new Fregadero();
		Lavaplatos lavaplatos = new Lavaplatos();

		for (int i = 0; i < numComensales; i++) {
			new Comensal(cantidadPlatos, barrera, mesa, fregadero).start();
		}
		lavaplatos.start();
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
