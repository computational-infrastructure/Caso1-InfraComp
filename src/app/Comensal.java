package app;

import java.util.concurrent.CyclicBarrier;

public class Comensal extends Thread
{
    private boolean tieneCubiertoT1;
    private boolean tieneCubiertoT2;
    private int platosPorComer;
    private CyclicBarrier barrera;

    public Comensal(int platos, CyclicBarrier barrera)
    {
        this.platosPorComer = platos;
        this.barrera = barrera;
    }


    public void run()
    {

    }

    public void comer()
    {

    }

    public void cogerCubiertos()
    {
        
    }
}
