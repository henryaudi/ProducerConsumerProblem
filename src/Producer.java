import java.util.Random;

public class Producer implements Runnable {
    // Data Fields
    private final int producerID;
    private final Buffer buffer;

    // Getter and setter
    public int getProducerID() { return this.producerID; }

    // Constructor
    public Producer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.producerID = id;
    }

    // Methods
    /** Producer adds a random number to the buffer */
    public void append() {
        Random rand = new Random();
        int num = rand.nextInt(Integer.MAX_VALUE -1);
        buffer.insert_item(num);
        System.out.println("Producer " + getProducerID() + " Inserted number " + num + " to the buffer.");
    }

    /** Producer sleeps for random amount of time (0-2 seconds) */
    public void sleep() {
        System.out.println("Producer " + getProducerID() + " is sleeping.");
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Execute the following code in the thread */
    @Override
    public void run() {
        while (true){
            sleep();
            append();
        }
    }
}
