package questao_5.letra_a;

import java.util.*;

public class Get implements Runnable {
    private Map<String, Integer> map;
    
    public Get(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
    	getOperations();
    }
    
    private void getOperations() {
    	Set<String> keys = this.map.keySet();
    	for (String string : keys) {
			this.map.remove(string);
		}
    }
    
}
