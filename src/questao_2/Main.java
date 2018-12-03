package questao_2;

import java.util.concurrent.CountDownLatch;

public class Main {
	
	static CountDownLatch lt;
	static Server m1;
	static Server m2;
	static Server m3;
	static final String ARRIVED_FIRST = "";
	static final String MIRROR_COM = "mirror1.com";
	static final String MIRROR_BR = "mirror2.br";
	static final String MIRROR_EDU = "mirror2.edu";
	
	public static void main(String[] args) throws InterruptedException {
			lt = new CountDownLatch(1);
			m1 = new Server(MIRROR_COM, lt, ARRIVED_FIRST);
			m2 = new Server(MIRROR_BR, lt, ARRIVED_FIRST);
			m3 = new Server(MIRROR_EDU, lt, ARRIVED_FIRST);
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
