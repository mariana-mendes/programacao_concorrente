package questao_3;

import java.util.concurrent.CountDownLatch;

public class Main {
	
	static CountDownLatch lt;
	static Server m1;
	static Server m2;
	static Server m3;
	
	public static void main(String[] args) throws InterruptedException {

		
		for (int i = 0; i < 20; i++) {
			lt = new CountDownLatch(1);
			m1 = new Server("mirror1.com", lt);
			m2 = new Server("mirror2.br", lt);
			m3 = new Server("mirror3.edu", lt);
			reliableRequest();
		}
	}

	public static String reliableRequest() throws InterruptedException {
		Thread a = new Thread(m1);
		Thread b = new Thread(m2);
		Thread c = new Thread(m3);

		a.start();
		b.start();
		c.start();
		
		lt.await();
		
		return "";
		
		

	}

}
