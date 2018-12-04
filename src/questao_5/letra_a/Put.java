package questao_5.letra_a;

import java.util.concurrent.*;
import java.util.*;


public class Put implements Runnable {
    private Map<String, Integer> map;
    private static int index;
    private int numInterations; 
    private final CountDownLatch latch;
    

    public Put(Map<String, Integer> map, int numInterations, CountDownLatch latch) {
        this.map = map;
        this.numInterations = numInterations;
        this.latch = latch;
    }

    private synchronized String getNextString() {
    	String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i< 10; i++){
            int number = new Random().nextInt(CHAR_LIST.length());
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    private synchronized int getNextInt() { return index++; }

    @Override
    public void run() {
    	this.putOperations();
    	latch.countDown();
    }
    
    private void putOperations() {
    	  while(numInterations > 0) {
             map.put(getNextString(), getNextInt());
             numInterations--;
          }
    }
}
