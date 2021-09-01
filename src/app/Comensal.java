package app;

public class Comensal extends Thread {

	public Boolean cambiando;

	public synchronized void cambiarCubiertos() {
		this.tieneCubiertoT1 = false;
		this.tieneCubiertoT2 = false;

		this.cambiando = true;
		while (this.cambiando) {
			if (Fregadero.recibirCubiertos()) {
				Comensal.yield();
			} else {
				this.cambiando = false;
			}
		}
	}
}
