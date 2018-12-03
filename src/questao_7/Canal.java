package questao_7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.regex.Pattern;

public class Canal{

	private Queue<String> fila1;
	private Queue<String> fila2;


	public Canal() {
		this.fila1 = new LinkedList<String>();
		this.fila2 = new LinkedList<String>();
	}
	

	public void putString() throws InterruptedException {
		while (true) {
			String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i< 10; i++){
	            int number = new Random().nextInt(CHAR_LIST.length());
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
			 fila1.add(randStr.toString());
			 this.notifyAll();
			 break;
		}
		this.wait();
	}

	public void  filterString() throws InterruptedException {
		while(!fila1.isEmpty()) {
			String regex = "(.)*(\\d)(.)*";      
			Pattern pattern = Pattern.compile(regex);
			String msg = fila1.peek();
			boolean containsNumber = pattern.matcher(msg).matches();

			Thread.sleep(1000);
			System.out.println(">>> Filter ... " + msg);
			Thread.sleep(1000);
			if(!containsNumber) {
				System.out.println("alpha value ");
				fila2.add(msg);
			}
			fila1.remove();
			System.out.println(" ");
			this.notifyAll();
		}
		this.wait();
	}
	
	public void printString() throws InterruptedException {
		while(!fila2.isEmpty()) {
			System.out.println(">> Print  " + fila2.peek());
			fila2.remove();
			this.notifyAll();
		}
		this.wait();
	}
	
}
