package questao_1;

public class Canal implements Channel {

	private int capacidade;
	
	    public Canal(int capacidade) {
	    	this.capacidade = capacidade;
	}

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
		@Override
		public void putMessage(String message) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String takeMessage() {
			// TODO Auto-generated method stub
			return null;
		}

}
