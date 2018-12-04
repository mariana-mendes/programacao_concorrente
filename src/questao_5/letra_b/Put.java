package questao_5.letra_b;

import java.util.concurrent.*;
import java.util.*;


public class Put implements Runnable {
    private List list;
    private static int index;
    private int numInterations; 
    private final CountDownLatch latch;
    

    public Put(List list, int numInterations, CountDownLatch latch) {
        this.list = list;
        this.numInterations = numInterations;
        this.latch = latch;
    }

    private synchronized int getNextInt() { return index++; }

    @Override
    public void run() {
    	this.putOperations();
    	latch.countDown();
    }
    
    private void putOperations() {
    	  while(numInterations > 0) {
             list.add(getNextInt());
             numInterations--;
          }
    }
}
