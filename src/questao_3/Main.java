package questao_3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

	static CountDownLatch lt;
	static Server m1;
	static Server m2;
	static Server m3;
	static Thread a,b,c;
	static final String STRING_VAZIA = "";

	public static void main(String[] args) throws InterruptedException {
		lt = new CountDownLatch(1);
		m1 = new Server("mirror1.com", lt, STRING_VAZIA);
		m2 = new Server("mirror2.br", lt, STRING_VAZIA);
		m3 = new Server("mirror3.edu", lt, STRING_VAZIA);
		System.out.println(reliableTimeout());
		
	}

	public static String reliableRequest() throws InterruptedException {
		a = new Thread(m1);
		b = new Thread(m2);
		c = new Thread(m3);
		a.start();
		b.start();
		c.start();

		String resultado;
		if(lt.await(2000, TimeUnit.MILLISECONDS)) {
			resultado = String.join(m1.getServer(), m2.getServer(), m3.getServer());
		}else {
			resultado = "Erro!";
		}
		
		return resultado;

	}

	public static String reliableTimeout() throws InterruptedException {
		return reliableRequest();
	}

}
