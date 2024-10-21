import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final CustomQueue customQueue = new CustomQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Void> task = () -> {
            customQueue.enqueue(RandomGenerator.getDefault().nextInt());
            return null;
        };
        Callable<Integer> dequeueTask = () -> {
            try {
                return customQueue.dequeue();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        };
        List<Callable<Void>> callables = new ArrayList<>();
        List<Callable<Integer>> dequeueCallables = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            callables.add(task);
        }

        for (int i = 0; i < 2000; i++) {
            dequeueCallables.add(dequeueTask);
        }

        executorService.invokeAll(callables);
        executorService.invokeAll(dequeueCallables);
        executorService.close();
        System.out.println(customQueue.queue.size());
    }
}