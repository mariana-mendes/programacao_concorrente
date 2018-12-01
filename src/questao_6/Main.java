package questao_6;

public class Main {

	private static Canal canal;

	public static void main(String[] args) throws InterruptedException {
		canal = new Canal();
		Thread t1 = new Thread(produces);
		Thread t2 = new Thread(consumes);
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}

	private static Runnable consumes = new Runnable() {
		public void run() {
			while (true) {
				synchronized (canal) {
					try {
						canal.takeNumber();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};

	private static Runnable produces = new Runnable() {
		public void run() {
			while (true) {
				synchronized (canal) {
					try {
						canal.putNumber();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};
}