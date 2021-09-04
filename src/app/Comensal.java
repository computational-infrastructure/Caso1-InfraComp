package app;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.Random;

public class Comensal extends Thread {
	private int id;
	private boolean tieneCubiertoT1;
	private boolean tieneCubiertoT2;
	private int platosPorComer;
	private CyclicBarrier barrera;
	private Mesa mesa;
	private int mitadDePlatos;
	private CyclicBarrier barreraFinal;
	public Boolean cambiando;

	public Comensal(int platos, CyclicBarrier barrera, Mesa mesa, int id, CyclicBarrier barreraFinal) {
		this.platosPorComer = platos;
		this.barrera = barrera;
		this.mesa = mesa;
		this.id = id;
		this.mitadDePlatos = platos / 2;
		this.barreraFinal = barreraFinal;
	}

	public void run() {
		comer();
	}

	public void comer() {
		Main.crearLogs("Comensal " + id + " empezó a comer");
		while (platosPorComer > 0) {
			cogerCubiertos();
			Main.crearLogs("Comensal " + id + " empieza a comer. Platos por comer: " + platosPorComer);
			if (mitadDePlatos == platosPorComer) {
				try {
					Main.crearLogs("Comensal " + id + " llegó a la mitad de sus platos.");
					barrera.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep((new Random().nextInt(5 - 3) + 3) * 1000);
				platosPorComer--;
				cambiarCubiertos();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			barreraFinal.await();
			Main.crearLogs("Todos los comensales terminaron de comer y se fueron para la casa.");
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public synchronized void cogerCubiertos() {
		if (!(tieneCubiertoT1 && tieneCubiertoT2)) {
			Main.crearLogs("Comensal " + id + " intenta coger un cubierto T1.");
			tieneCubiertoT1 = mesa.ponerCubiertosT1();
			if (!tieneCubiertoT1) {
				Main.crearLogs("Comensal " + id + " no pudo coger un cubierto T1.");
				mesa.esperar();
				cogerCubiertos();
				if ((tieneCubiertoT1 && tieneCubiertoT2)) {
					return;
				}
			}
			Main.crearLogs("Comensal " + id + " intenta coger un cubierto T2.");
			tieneCubiertoT2 = mesa.ponerCubiertosT2();
			if (!tieneCubiertoT2) {
				Main.crearLogs("Comensal " + id + " no pudo coger un cubierto T2.");
				mesa.recogerCubiertosT1();
				Main.crearLogs("Comensal " + id + " devolvió un cubierto T1.");
				this.tieneCubiertoT1 = false;
				mesa.esperar();
				cogerCubiertos();
				if ((tieneCubiertoT1 && tieneCubiertoT2)) {
					return;
				}
			}
		}
	}

	public synchronized void cambiarCubiertos() {
		this.tieneCubiertoT1 = false;
		this.tieneCubiertoT2 = false;

		this.cambiando = true;
		while (this.cambiando) {
			if (Fregadero.recibirCubiertos()) 
			{
				Main.crearLogs("Comensal " + id + " está esperando a que se desocupe el fregadero");
				Comensal.yield();
			} 
			else 
			{
				this.cambiando = false;
				Main.crearLogs("Comensal " + id + " cambió de cubiertos");
			}
		}
	}
}
