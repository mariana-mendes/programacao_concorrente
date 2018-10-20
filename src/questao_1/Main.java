package questao_1;


public class Main{ 
	
	public static void main(String[] args) {
		Canal canal = new Canal(5);
		new Thread(t1).start();
		new Thread(t2).start();
	}

	private static Runnable t1 = new Runnable() {
		public void run() {

		}
	};

	private static Runnable t2 = new Runnable() {
		public void run() {

		}
	};
}