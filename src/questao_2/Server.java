package questao_2;

import java.util.concurrent.Phaser;

public class Server implements Runnable{
	
	private String serverName;
	private Phaser phaser;

	public Server(String serverName, Phaser phaser) {
		this.serverName = serverName;
		this.phaser = phaser;
		phaser.register();
		System.out.println("nome " + this.serverName + " phaser: " + this.phaser);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("server " + this.serverName + " deregister kkakkak " +  this.phaser);
		this.phaser.arriveAndDeregister();
		
	}

}
