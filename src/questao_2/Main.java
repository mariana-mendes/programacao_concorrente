package questao_2;

import java.util.concurrent.Phaser;

public class Main {
	
	
	static Phaser ph = new Phaser(1);
	
	static Server m1 = new Server("mirror1.com", ph);
	static Server m2 = new Server("mirror2.br", ph);
	static Server m3 = new Server("mirror3.edu", ph);
	
	public static void main(String[] args) {

		System.out.println(reliableRequest());
		
	}
	
	
	public static String reliableRequest() {
		new Thread(m1).start();
		new Thread(m2).start();
		new Thread(m3).start();
		return "" + ph.arriveAndAwaitAdvance();
		
	}

}
