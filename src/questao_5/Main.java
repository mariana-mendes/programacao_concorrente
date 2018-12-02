package questao_5;

import java.util.concurrent.*;
import java.util.*;


public class Main implements Runnable {
	private static Experiment exp;
    private Map<String, Integer> map;
    private static int index;
    private int numInterations; 
    private Random random = new Random();
    private final CountDownLatch latch;
    

    public Main(Map<String, Integer> map, int numInterations, CountDownLatch latch) {
        this.map = map;
        this.numInterations = numInterations;
        this.latch = latch;
        this.exp = new Experiment();
    }

    private synchronized String getNextString() {
        byte[] array = new byte[7];
        this.random.nextBytes(array);
        String generatedString = new String(array);
        
        return generatedString;
    }

    private synchronized int getNextInt() { return index++; }

    @Override
    public void run() {
        while(numInterations > 0) {
        	int nextInt = getNextInt();
            map.put(getNextString(), nextInt);
            System.out.println("Insert " + nextInt + " into " + this.map.getClass().getSimpleName());
            numInterations--;
        }
        latch.countDown();
    }


    public static void main(String[] args) throws InterruptedException {
        ArrayList<Long> syncResult = new ArrayList<Long>(); 
        ArrayList<Long> concurrentMapResult = new ArrayList<Long>(); 

        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        Map<String, Integer> concurrent = new ConcurrentHashMap<>();

        System.out.println("Start insert...");
        
        //Rodar 5 vezes as n iterações definidas em Experiment;
        for(int i = 0; i < 5; i++) {
            syncResult.add(exp.testMap(syncMap)); 
            concurrentMapResult.add(exp.testMap(concurrent));
        }
        System.out.println(" Collections.synchronizedMap -->  time difference: " + syncResult +  " milliseconds");
        System.out.println(" ConcurrentMap               -->  time difference: " + concurrentMapResult +  " milliseconds");


    }

}
