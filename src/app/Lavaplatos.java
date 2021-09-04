package app;

import java.util.Random;

public class Lavaplatos extends Thread {

	public Boolean recibiendo;
	private Mesa mesa;

	public Lavaplatos(Mesa mesa) {
		this.mesa = mesa;
	}

	@Override
	public void run() {
		lavar();
	}

	public void lavar() {
		while (true) {
			this.recibiendo = true;
			while (this.recibiendo) {
				if (Fregadero.entregarCubiertos()) {
					Thread.yield();
					Main.crearLogs("Lavaplatos está esperando que hayan cubiertos en el fregadero.");
				} else {
					this.recibiendo = false;
					Main.crearLogs("Lavaplatos recogió cubiertos del fregadero.");
				}
			}

			try {
				Main.crearLogs("Lavando Cubiertos");
				Thread.sleep(new Random().nextInt(3) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			mesa.recogerCubiertosLavaplatos();
		}
	}
}
