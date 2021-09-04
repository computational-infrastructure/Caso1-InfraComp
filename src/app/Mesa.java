package app;

public class Mesa 
{
	public static Integer numCubiertosT1;
	public static Integer numCubiertosT2;

	public Mesa(Integer numCubiertosT1, Integer numCubiertosT2) {
		Mesa.numCubiertosT1 = numCubiertosT1;
		Mesa.numCubiertosT2 = numCubiertosT2;
	}

	public synchronized boolean ponerCubiertosT1() {
		if (numCubiertosT1 > 0) {
			numCubiertosT1--;
			return true;
		}

		return false;
	}

	public synchronized boolean ponerCubiertosT2() 
	{
		if (numCubiertosT2 > 0) 
		{
			numCubiertosT2--;
			return true; 
		}

		return false;
	}

	public void recogerCubiertosT1() 
	{
		numCubiertosT1++;
		synchronized (this)
		{
			notifyAll();
			Main.crearLogs("Se dejó un cubierto tipo T1 en la mesa. Cantidad disponible T1: " + numCubiertosT1 + " | Cantidad disponible T2: "+numCubiertosT2);
		}
	}

	public void recogerCubiertosT2()
	{
		numCubiertosT2++;
		synchronized (this)
		{
			notifyAll();
		}
		Main.crearLogs("Se dejó un cubierto tipo T2 en la mesa. Cantidad disponible T1: " + numCubiertosT1 + " | Cantidad disponible T2: " + numCubiertosT2);
	}

	public void recogerCubiertosLavaplatos()
	{
		numCubiertosT1++;
		numCubiertosT2++;
		synchronized (this)
		{
			notifyAll();
			Main.crearLogs("Cubiertos dejados en la mesa");
			Main.crearLogs("Cantidad de cubiertos en la mesa: T1 = " + numCubiertosT1 + " | T2 = " +numCubiertosT2);
		}
	}

	public void esperar()
	{
		try 
		{
			synchronized(this)
			{
				wait();
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

}
