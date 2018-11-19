package questao_2;

import java.util.concurrent.Phaser;

public class Main {
	
	public static void main(String[] args) {
		
		HTTPRequest http = new HTTPRequest();
		
		Phaser ph = new Phaser();
	
		Server m1 = new Server("mirror1.com", ph);
		Server m2 = new Server("mirror2.br", ph);
		Server m3 = new Server("mirror3.edu", ph);
		
		new Thread(m1).start();
		new Thread(m2).start();
		new Thread(m3).start();
		
		System.out.println("esperando essa bomba: " + ph.toString());
		
		ph.arriveAndAwaitAdvance();
		System.out.println(ph.getArrivedParties());
		ph.arriveAndDeregister();
		
	}
	
	
	

}
