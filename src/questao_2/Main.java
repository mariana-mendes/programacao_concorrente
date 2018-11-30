package questao_2;

import java.util.concurrent.CountDownLatch;

public class Main {
	
	static CountDownLatch lt;
	static Server m1;
	static Server m2;
	static Server m3;
	static final String STRING_VAZIA ="";
	
	public static void main(String[] args) throws InterruptedException {
			lt = new CountDownLatch(1);
			m1 = new Server("mirror1.com", lt, STRING_VAZIA);
			m2 = new Server("mirror2.br", lt, STRING_VAZIA);
			m3 = new Server("mirror3.edu", lt, STRING_VAZIA);
			System.out.println(reliableRequest());
	}

	public static String reliableRequest() throws InterruptedException {
		Thread a = new Thread(m1);
		Thread b = new Thread(m2);
		Thread c = new Thread(m3);

		a.start();
		b.start();
		c.start();
		lt.await();
		
		String retorno = String.join(m1.getServer(), m2.getServer(), m3.getServer());
		return retorno;
	}

}
