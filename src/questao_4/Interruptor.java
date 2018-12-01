package questao_4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class Interruptor implements Runnable{
	
	private CountDownLatch countDownLatch;

	public Interruptor(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
 
	
	@Override
	public void run() {
		try {
			Thread.sleep((ThreadLocalRandom.current().nextInt(20, 30))*1000);
			synchronized (countDownLatch) {
				countDownLatch.countDown();
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
			
	}

}
