package app;

public class Mesa 
{
	public static Integer numCubiertosT1;
	public static Integer numCubiertosT2;

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

	public void recogerCubiertosT1() 
	{
		numCubiertosT1++;
		notifyAll();
	}

	public void recogerCubiertosT2()
	{
		numCubiertosT2++;
		notifyAll();
	}

	public void recogerCubiertosLavaplatos()
	{
		numCubiertosT1++;
		numCubiertosT2++;
		notifyAll();
	}

	public void esperar()
	{
		try 
		{
			wait();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}
