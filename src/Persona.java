import java.util.Random;

public class Persona extends Thread {
	
	public static int i = 0;
	public int id;
	public Persona() {
		id = i;
		i++;
	}

	public void run() {
		if(Data.buffer[(Data.Out+1) % Data.N] != -1) {			
			try {
				sleep(Data.min + new Random().nextInt(Data.max - Data.min));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		if(Data.buffer[Data.Out] == -1) {			
			Data.mutex.down();		//sedie
			Data.buffer[Data.Out] = id;
			System.out.println(id + " si è seduto in posizione " + Data.Out);	
			Data.Out = (Data.Out+1) % Data.N;
			Data.mutex.up();
			Data.mutexc.down();
		}else {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Addio " + id);
		}
		
	}
}
