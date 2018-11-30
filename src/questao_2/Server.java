package questao_2;

import java.util.concurrent.CountDownLatch;

public class Server implements Runnable {

	private String serverName;
	private CountDownLatch countDownLatch;
	private HTTPRequest http = new HTTPRequest();
	private String arrivedFirst;

	/**
	 * Constroi um servidor.
	 * 
	 * @param serverName
	 *            Nome da instancia do servidor.
	 * @param countDownLatch
	 *            latch usado para controlar os 3 servidores (threads)
	 * @param arrivedFirst
	 *            será uma string vazia se o servidor não for o que tiver feito a
	 *            requisição ou será uma string com o nome do servidor se o mesmo
	 *            tiver feito a requisição.
	 */
	public Server(String serverName, CountDownLatch countDownLatch, String arrivedFirst) {
		this.serverName = serverName;
		this.countDownLatch = countDownLatch;
		this.arrivedFirst = "";
	}
 
	@Override
	public void run() {
		try {
			Thread.sleep(10);
			synchronized (countDownLatch) {
				if (countDownLatch.getCount() > 0) {
					this.setServer(http.request(serverName));
					countDownLatch.countDown();
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public String getServer() {
		return arrivedFirst;
	}

	public void setServer(String server) {
		this.arrivedFirst = server;
	}
}
