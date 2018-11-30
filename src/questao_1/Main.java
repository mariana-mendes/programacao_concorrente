package questao_1;

public class Main {

	private static Canal canal;

	public static void main(String[] args) throws InterruptedException {
		canal = new Canal(5);
		Thread t1 = new Thread(produces);
		Thread t2 = new Thread(consumes);
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
//		System.out.println(canal.getCount());
		
	}

	private static Runnable consumes = new Runnable() {
		public void run() {
//			while (true) {
				synchronized (canal) {
					try {
						canal.takeMessage();
						canal.takeMessage();
//						canal.takeMessage();
//						canal.takeMessage();
//						canal.takeMessage();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		}
	};

	private static Runnable produces = new Runnable() {
		public void run() {
//			while (true) {
				// sincronizando o dado que será usado, região crítica.
				synchronized (canal) {
					try {
						canal.putMessage("oi garouta");
						canal.putMessage("alo");
//						canal.putMessage("das");
//						canal.putMessage("odasie");
//						canal.putMessage("oidasde");
//						canal.putMessage("oieaaaaa");

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//			}
		}
	};
}