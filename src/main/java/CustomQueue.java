import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {

    final Queue<Integer> queue = new LinkedList<>();

    public synchronized void enqueue(int value) {
        queue.add(value);
    }

    public synchronized int dequeue() {
        return queue.poll();
    }

}
