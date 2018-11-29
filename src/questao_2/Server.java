package questao_2;

import java.util.concurrent.CountDownLatch;

public class Server implements Runnable{
	
	private String serverName;
	private CountDownLatch countDownLatch;
	HTTPRequest http = new HTTPRequest();
	private boolean arrive;

	public Server(String serverName, CountDownLatch countDownLatch, boolean arrive) {
		this.serverName = serverName;
		this.countDownLatch = countDownLatch;
		this.arrive = arrive;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
			if (arrive) {
				Thread.currentThread().interrupt();
			} else {
				http.request(serverName);
				arrive = true;
				countDownLatch.countDown();
			}
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();;
		}
		
		
	}

	public boolean isArrive() {
		return arrive;
	}

	public void setArrive(boolean arrive) {
		this.arrive = arrive;
	}
	
	

}
