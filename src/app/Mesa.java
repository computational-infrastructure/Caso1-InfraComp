package app;

public class Mesa {
	private static Integer numCubiertosT1;
	private static Integer numCubiertosT2;

	public Mesa(Integer numCubiertosT1, Integer numCubiertosT2) {
		Mesa.numCubiertosT1 = numCubiertosT1;
		Mesa.numCubiertosT2 = numCubiertosT2;
	}

	public static synchronized boolean ponerCubiertosT1() {
		if (numCubiertosT1 > 0) {
			numCubiertosT1--;
			return true; // TODO: set Comensal.tieneCubiertoT1 with this return.
		}

		return false;
	}

	public static synchronized boolean ponerCubiertosT2() {
		if (numCubiertosT2 > 0) {
			numCubiertosT2--;
			return true; // TODO: set Comensal.tieneCubiertoT2 with this return.
		}

		return false;
	}

	public static void recogerCubiertosT1() 
	{
		numCubiertosT1++;
	}

	public static void recogerCubiertosT2()
	{
		numCubiertosT2++;
	}

	public void esperar() throws InterruptedException
	{
		wait();
	}
}
