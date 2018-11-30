package questao_6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Canal{

	private Queue<Integer> fila;

	public Canal() {
		this.fila = new LinkedList<Integer>();
	}
	

	public void putNumber() throws InterruptedException {
		while (true) {
			int num = new Random().nextInt(20);
			if(num%2 == 0) {
				System.out.print(">>");
				Thread.sleep(2000);
				System.out.print(">> ");
				System.out.println(num + " foi inserido");
				System.out.println(" ");
				fila.add(num);
				break;
			}
		}
		notifyAll();
	}

	public void  takeNumber() throws InterruptedException {
		while(!fila.isEmpty()) {
			int number = fila.peek();
			Thread.sleep(2000);
			System.out.print(">>");
			Thread.sleep(1000);
			System.out.print(">> ");
			System.out.println(number + " foi removido");
			System.out.println(" ");
			fila.remove();
			this.notifyAll();
		}
		this.wait();
	}
	
}
