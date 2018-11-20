package questao_2;

import java.util.concurrent.Phaser;

public class Server implements Runnable{
	
	private String serverName;
	private Phaser phaser;
	HTTPRequest http = new HTTPRequest();

	public Server(String serverName, Phaser phaser) {
		this.serverName = serverName;
		this.phaser = phaser;
		phaser.register();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
			http.request(serverName);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.phaser.arriveAndDeregister();
		
	}

}
