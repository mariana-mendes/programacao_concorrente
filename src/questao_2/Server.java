package questao_2;

import java.util.concurrent.CountDownLatch;

public class Server implements Runnable{
	
	private String serverName;
	private CountDownLatch countDownLatch;
	HTTPRequest http = new HTTPRequest();

	public Server(String serverName, CountDownLatch countDownLatch) {
		this.serverName = serverName;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
			System.out.println(http.request(serverName));
			countDownLatch.countDown();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
