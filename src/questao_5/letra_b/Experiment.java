package questao_5.letra_b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
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

        List list = new ArrayList();
        List syncList = Collections.synchronizedList(list);
        
        List copyOnWrite = new CopyOnWriteArrayList();

        for(int i = 0; i < 5; i++) {
        	syncResultPut.add(testList(syncList)); 
        	concurrentMapResultPut.add(testList(copyOnWrite));
        	syncResultGet.add(testListGet(syncList));
        	concurrentMapResultGet.add(testListGet(copyOnWrite));
        }
        System.out.println(" Collections.synchronizedMap -->  time difference: " + syncResultPut +  " milliseconds");
        System.out.println(" ConcurrentMap               -->  time difference: " + concurrentMapResultPut +  " milliseconds");
        
        System.out.println(" Collections.synchronizedMapGet -->  time difference: " + syncResultGet +  " milliseconds");
        System.out.println(" ConcurrentMapGet               -->  time difference: " + concurrentMapResultGet +  " milliseconds");
    }

	static Long testList(List list) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(nThreads);

        long startTime = System.currentTimeMillis();
            ExecutorService exec = Executors.newFixedThreadPool(nThreads);
            for(int i = 0; i < nThreads; i++) {
                exec.submit(new Put(list, inserts, latch));
            }
            latch.await();  
            exec.shutdown();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
	
	static Long testListGet(List list) throws InterruptedException{
        long startTime = System.currentTimeMillis();
            ExecutorService exec = Executors.newFixedThreadPool(nThreads);
            for(int i = 0; i < nThreads; i++) {
            	exec.submit(new Get(list));
            }
            exec.shutdown();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
}