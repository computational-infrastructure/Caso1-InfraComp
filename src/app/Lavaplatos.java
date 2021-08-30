package app;
import java.util.Random;

public class Lavaplatos extends Thread 
{

    public void run()
    {
        lavar();
    }
    public void lavar()
    {
        while (true)
        {
            //Fregadero.entregarCubiertos();
            try 
            {
                Thread.sleep(new Random().nextInt(3));
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            //Mesa.recogerCubiertos();
        }
    }
}
