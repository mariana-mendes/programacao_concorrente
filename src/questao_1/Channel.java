package questao_1;

public interface Channel {

	public void putMessage(String message) throws InterruptedException;
	public String takeMessage() throws InterruptedException;

}
