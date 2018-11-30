package questao_1;

import java.util.LinkedList;
import java.util.Queue;

public class Canal implements Channel {

	private Queue<String> fila;
	private int count;
	private int capacidade;

	public Canal(int capacidade) {
		this.capacidade = capacidade;
		this.fila = new LinkedList<String>();
		this.count = 0;
	}
	

	@Override
	public void putMessage(String message) throws InterruptedException {
		while (this.getCount() < this.getCapacidade()) {
			System.out.println(message + " está no buffer");
			fila.add(message);
			count++;
			this.notifyAll();
			return;
		}
		this.wait();
	}

	@Override
	public String takeMessage() throws InterruptedException {
		while(!fila.isEmpty()) {
			String message = fila.peek();
			System.out.println(message + " foi removida");
			fila.remove();
			this.notifyAll();
			count--;
			return message;
		}
		this.wait();
		return "Error";
	}
	
	public int getCapacidade() {
		return capacidade;
	}


	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}


	public int getCount() {
		return this.count;
	}



}
