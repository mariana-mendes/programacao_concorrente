package questao_5.letra_b;

import java.util.*;

public class Get implements Runnable {
    private List list;
    
    public Get(List list) {
        this.list = list;
    }

    @Override
    public void run() {
    	getOperations();
    }
    
    private void getOperations() {
	    for (int i = 0; i < list.size(); i++) {
			this.list.remove(i);
		}
    }
    
}
