package questao_5.letra_a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Experiment {
	static final int inserts = 1;
	static final int nThreads = 1000;
	
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Long> syncResultPut = new ArrayList<Long>(); 
        ArrayList<Long> concurrentMapResultPut = new ArrayList<Long>(); 
        
        ArrayList<Long> syncResultGet = new ArrayList<Long>(); 
        ArrayList<Long> concurrentMapResultGet = new ArrayList<Long>(); 

        Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        Map<String, Integer> concurrent = new ConcurrentHashMap<>();

        for(int i = 0; i < 5; i++) {
        	syncResultPut.add(testMap(syncMap)); 
        	concurrentMapResultPut.add(testMap(concurrent));
        	syncResultGet.add(testMapGet(syncMap));
        	concurrentMapResultGet.add(testMap(concurrent));
        }
        System.out.println(" Collections.synchronizedMap -->  time difference: " + syncResultPut +  " milliseconds");
        System.out.println(" ConcurrentMap               -->  time difference: " + concurrentMapResultPut +  " milliseconds");
        
        System.out.println(" Collections.synchronizedMapGet -->  time difference: " + syncResultGet +  " milliseconds");
        System.out.println(" ConcurrentMapGet               -->  time difference: " + concurrentMapResultGet +  " milliseconds");
    }

	static Long testMap(Map<String, Integer> map) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(nThreads);

        long startTime = System.currentTimeMillis();
            ExecutorService exec = Executors.newFixedThreadPool(nThreads);
            for(int i = 0; i < nThreads; i++) {
                exec.submit(new Put(map, inserts, latch));
            }
            latch.await();  
            exec.shutdown();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
	
	static Long testMapGet(Map<String, Integer> map) throws InterruptedException{
        long startTime = System.currentTimeMillis();
            ExecutorService exec = Executors.newFixedThreadPool(nThreads);
            for(int i = 0; i < nThreads; i++) {
            	exec.submit(new Get(map));
            }
            exec.shutdown();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
}