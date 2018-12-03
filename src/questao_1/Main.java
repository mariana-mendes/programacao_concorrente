package questao_1;

import java.util.Random;

public class Main {

	private static Canal canal;
	private static int i;

	public static void main(String[] args) throws InterruptedException {
		i = 0;
		canal = new Canal(5);
		Thread t1 = new Thread(produces);
		Thread t2 = new Thread(consumes);
		t2.start();
		t1.start();

		t1.join();
		t2.join();

	}

	private static Runnable consumes = new Runnable() {
		public synchronized void run() {
			while (true) {
				try {
					Thread.sleep(new Random().nextInt(2000));
					System.out.println("Consome " + canal.takeMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	};

	private static Runnable produces = new Runnable() {
		public synchronized void run() {
			while (true) {
				try {
					Thread.sleep(new Random().nextInt(2000));
					System.out.println("Produz " + i);
					canal.putMessage("Mensagem de número " + i);
					i++;
					
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	};
}