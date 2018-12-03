package questao_1;

import java.util.LinkedList;
import java.util.Queue;

public class ChannelImpl implements Channel {

	private Queue<String> queue;
	private int count;
	private int capacity;

	public ChannelImpl(int capacidade) {
		this.capacity = capacidade;
		this.queue = new LinkedList<String>();
		this.count = 0;
	}

	@Override
	public void putMessage(String message) throws InterruptedException {
		synchronized (queue) {
			while (!(this.getCount() < this.getCapacity())) {
				this.queue.wait();
			}
			queue.add(message);
			count++;
			this.queue.notifyAll();
		}
	}

	@Override
	public String takeMessage() throws InterruptedException {
		synchronized (queue) {
			while (queue.isEmpty()) {
				this.queue.wait();
			}
			String message = queue.peek();
			queue.remove();
			count--;
			this.queue.notifyAll();
			return message;
		}
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacidade) {
		this.capacity = capacidade;
	}

	public int getCount() {
		return this.count;
	}

}
