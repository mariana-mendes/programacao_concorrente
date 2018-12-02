package questao_5.letra_b;

import java.util.concurrent.*;
import java.util.*;


public class Main implements Runnable {
	private static Experiment exp;
    private List<Integer> list;
    private static int index;
    private int numInterations; 
    private Random random = new Random();
    private final CountDownLatch latch;
    

    public Main(List<Integer> list, int numInterations, CountDownLatch latch) {
        this.list = list;
        this.numInterations = numInterations;
        this.latch = latch;
        this.exp = new Experiment();
    }

    private synchronized int getNextInt() { return index++; }

    @Override
    public void run() {
        while(numInterations > 0) {
        	int nextInt = getNextInt();
        	list.add(nextInt);
//            System.out.println("Insert " + nextInt + " into " + this.map.getClass().getSimpleName());
            numInterations--;
        }
        latch.countDown();
    }


    public static void main(String[] args) throws InterruptedException {
        ArrayList<Long> syncResult = new ArrayList<Long>(); 
        ArrayList<Long> concurrentMapResult = new ArrayList<Long>(); 

        List list = new ArrayList();
        List syncList = Collections.synchronizedList(list);
        
        List copyOnWrite = new CopyOnWriteArrayList();

        System.out.println("Start insert...");
        
        //Rodar 5 vezes as n iterações definidas em Experiment;
        for(int i = 0; i < 5; i++) {
            syncResult.add(exp.testMap(syncList)); 
            concurrentMapResult.add(exp.testMap(copyOnWrite));
        }
        System.out.println(" Collections.synchronizedList -->  time difference: " + syncResult +  " milliseconds");
        System.out.println(" CopyOnWriteArrayList         -->  time difference: " + concurrentMapResult +  " milliseconds");


    }

}
