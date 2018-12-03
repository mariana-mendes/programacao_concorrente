package questao_7;

public class Main {

	private static Canal canal;

	public static void main(String[] args) throws InterruptedException {
		canal = new Canal();
		Thread t1 = new Thread(putString);
		Thread t2 = new Thread(stringGenerate);
		Thread t3= new Thread(stringPrint);
		t1.start();
		t2.start();
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
	}

	private static Runnable putString = new Runnable() {
		public void run() {
			while (true) {
				synchronized (canal) {
					try {
						canal.putString();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};
	
	private static Runnable stringGenerate = new Runnable() {
		public void run() {
			while (true) {
				synchronized (canal) {
					try {
						canal.filterString();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};
	
	private static Runnable stringPrint = new Runnable() {
		public void run() {
			while (true) {
				synchronized (canal) {
					try {
						canal.printString();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};


}