package questao_3;

import java.util.concurrent.CountDownLatch;

public class Server implements Runnable{
	
	private String serverName;
	private CountDownLatch countDownLatch;
	private HTTPRequest http = new HTTPRequest();

	public Server(String serverName, CountDownLatch countDownLatch) {
		this.serverName = serverName;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			synchronized(countDownLatch) {
				if (countDownLatch.getCount() > 0) {
					System.out.println(http.request(serverName));
					countDownLatch.countDown();
				} 
			}
			

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		
	}

	
	

}
