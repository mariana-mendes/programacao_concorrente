package questao_2;

import java.util.concurrent.CountDownLatch;

public class Main {
	
	static CountDownLatch lt = new CountDownLatch(3);
	
	static Server m1 = new Server("mirror1.com", lt);
	static Server m2 = new Server("mirror2.br", lt);
	static Server m3 = new Server("mirror3.edu", lt);
	
	public static void main(String[] args) throws InterruptedException {

		System.out.println(reliableRequest());
		
	}
	
	
	public static String reliableRequest() throws InterruptedException {
		new Thread(m1).start();
		new Thread(m2).start();
		new Thread(m3).start();
		lt.await();
		return "";
		
	}

}
