import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class Buffer {
    // Data fields
    private final int BUFFER_SIZE;
    private final ArrayBlockingQueue<Integer> buffer;
    private Semaphore sem;

    // Constructor
    public Buffer(int buffer_size) {
        this.BUFFER_SIZE = buffer_size;
        this.buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);
        this.sem = new Semaphore(0);    // Initialize the semaphore with 0 permit
    }

    // Getters and setters
    /** Get the buffer data structure from buffer */
    public Queue<Integer> getBuffer() { return this.buffer; }

    /** Returns the BUFFER_SIZE in setting */
    public int getBUFFER_SIZE() { return this.BUFFER_SIZE; }

    // Methods
    /** Remove the object at the head of the buffer.
        @return: {true} if removing item was successful, {false} otherwise
    */
    public boolean remove_item() {
        boolean item = false;
        try {
            sem.acquire();
            buffer.take();   // Removes the item from the buffer
            item = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return item;
    }

    /** Add a number to the tail of the buffer queue.
        @param num: the number to be added
        @return: {true} if the number was added successfully, {false} otherwise
    */
    public boolean insert_item(int num) {
        boolean isInserted = false;

        // Add one permit to the semaphore each time inserting a number.
        sem.release();
        int numPermits = sem.availablePermits();
        try {
            // Acquire all permits.
            sem.acquire(numPermits);
            buffer.put(num);    // Inserts the item into the buffer after entering the critical section
            isInserted = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Release all permits.
            sem.release(numPermits);
        }

        return isInserted;
    }
}
