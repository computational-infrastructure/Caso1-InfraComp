package app;

public class Fregadero {
	private static Integer tamFregadero;
	private static Integer numCubiertosT1;
	private static Integer numCubiertosT2;

	public Fregadero(Integer tamFregadero) {
		Fregadero.tamFregadero = tamFregadero;
		Fregadero.numCubiertosT1 = 0;
		Fregadero.numCubiertosT2 = 0;
	}

	public static boolean recibirCubiertos() {
		if (tamFregadero > numCubiertosT1 || tamFregadero > numCubiertosT2) {
			numCubiertosT1++;
			numCubiertosT2++;
			return false;
		}

		return true;
	}

	public static boolean entregarCubiertos() {
		if (numCubiertosT1 > 0 || numCubiertosT2 > 0) {
			numCubiertosT1--;
			numCubiertosT2--;
			return false;
		}

		return true;
	}
}
