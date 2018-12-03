package questao_1;

import java.util.Random;

public class Main {

	private static Channel channel;
	private static int messageID = 0;

	public static void main(String[] args) throws InterruptedException {
		channel = new ChannelImpl(5);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t2.start();
		t1.start();
		t1.join();
		t2.join();
	}

	private static Runnable consumer = new Runnable() {
		public synchronized void run() {
			while (true) {
				try {
					Thread.sleep(new Random().nextInt(2000));
					System.out.println("Consume message with ID: " + channel.takeMessage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private static Runnable producer = new Runnable() {
		public synchronized void run() {
			while (true) {
				try {
					Thread.sleep(new Random().nextInt(2000));
					System.out.println("Produces message with ID: " + messageID);
					channel.putMessage("" + messageID);
					messageID++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
}