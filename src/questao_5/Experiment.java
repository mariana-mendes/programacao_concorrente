package questao_5;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Experiment {
	static final int nIteration = 10;
	static final int nThreads = 2;

	static Long testMap(Map<String, Integer> map) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(nThreads);
        long startTime = System.currentTimeMillis();
            ExecutorService exec = Executors.newFixedThreadPool(nThreads);
            for(int i = 0; i < nThreads; i++)
                exec.submit(new Main(map, nIteration, latch));
            latch.await();  
            exec.shutdown();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }
}