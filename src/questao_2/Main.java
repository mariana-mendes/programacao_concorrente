package questao_2;

import java.util.concurrent.CountDownLatch;

public class Main {
	
	static CountDownLatch lt;
	static Server m1;
	static Server m2;
	static Server m3;
	static boolean arrive;
	
	public static void main(String[] args) throws InterruptedException {
		arrive = false;
		lt = new CountDownLatch(1);
		m1 = new Server("mirror1.com", lt, arrive);
		m2 = new Server("mirror2.br", lt, arrive);
		m3 = new Server("mirror3.edu", lt, arrive);

		System.out.println(reliableRequest());
		System.out.println(m1.isArrive() + " " + m2.isArrive() + " " +m3.isArrive());

	}

	public static String reliableRequest() throws InterruptedException {
		Thread a = new Thread(m1);
		Thread b = new Thread(m2);
		Thread c = new Thread(m3);

		a.start();
		b.start();
		c.start();
		
		
		
		lt.await();

		a.interrupt();
		b.interrupt();
		c.interrupt();

		return "";

	}

}
