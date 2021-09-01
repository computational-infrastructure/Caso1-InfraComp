package app;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.Random;

public class Comensal extends Thread {
	private boolean tieneCubiertoT1;
	private boolean tieneCubiertoT2;
	private int platosPorComer;
	private CyclicBarrier barrera;
	private Mesa mesa;
	private Fregadero fregadero;
	public Boolean cambiando;

	public Comensal(int platos, CyclicBarrier barrera, Mesa mesa, Fregadero fregadero) {
		this.platosPorComer = platos;
		this.barrera = barrera;
		this.mesa = mesa;
		this.fregadero = fregadero;
	}

	public void run() {
		comer();
	}

	public void comer() {
		while (platosPorComer > 0) {
			cogerCubiertos();
			int cantidad = Main.cantidadPlatos / 2;
			if (cantidad == platosPorComer) {
				try {
					barrera.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(new Random().nextInt(5 - 3) + 3);
					cambiarCubiertos();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void cogerCubiertos() {
		/*
		 * if(Mesa.numCubiertosT1 == 0 && !tieneCubiertoT1) { wait(); cogerCubiertos();
		 * } elseif(Mesa.numCubiertosT2 == 0 && tieneCubiertoT1) {
		 * Mesa.ponerCubiertosT1(); this.tieneCubiertoT1 = false; wait();
		 * cogerCubiertos(); } elseif(!tieneCubiertoT1) { this.tieneCubiertoT1 = true; }
		 * elseif(!tieneCubiertoT2) { this.tieneCubiertoT2 = tru e; }
		 */
	}

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
