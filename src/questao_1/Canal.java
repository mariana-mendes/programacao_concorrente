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
		synchronized (fila) {
			while (!(this.getCount() < this.getCapacidade())) {
				this.fila.wait();
			}
			fila.add(message);
			count++;
			this.fila.notifyAll();

		}
	}

	@Override
	public String takeMessage() throws InterruptedException {
		synchronized (fila) {
			while (fila.isEmpty()) {
				this.fila.wait();
			}
			String message = fila.peek();
			fila.remove();
			count--;
			this.fila.notifyAll();
			return message;
		}
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
