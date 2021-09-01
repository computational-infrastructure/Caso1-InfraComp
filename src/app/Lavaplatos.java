package app;

import java.util.Random;

public class Lavaplatos extends Thread {

	public Boolean recibiendo;

	public void run() {
		lavar();
	}

	public void lavar() {
		while (true) {

			this.recibiendo = true;
			while (this.recibiendo) {
				if (Fregadero.entregarCubiertos()) {
					Lavaplatos.yield();
				} else {
					this.recibiendo = false;
				}
			}

			try {
				Thread.sleep(new Random().nextInt(3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Mesa.recogerCubiertos();
		}
	}
}
