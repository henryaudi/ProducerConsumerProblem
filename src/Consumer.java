public class Consumer implements Runnable {
    // Data Fields
    private final int consumerID;

    private final Buffer buffer;

    // Constructor
    public Consumer(Buffer buffer, int consumerID) {
        this.consumerID = consumerID;
        this.buffer = buffer;
    }

    // Getter and setter
    /** Return the customer ID */
    public int getConsumerID() { return this.consumerID; }

    /** Return the buffer object associated with the customer */
    public Buffer getBuffer() { return this.buffer; }

    // Methods
    /** Consumer takes one number from the buffer */
    public void take() {
        int res = Integer.MAX_VALUE;
        if (!this.getBuffer().getBuffer().isEmpty()) { res = this.getBuffer().getBuffer().peek(); }
        this.getBuffer().remove_item();
        System.out.println("Consumer " + getConsumerID()+" consumes number " + res +" from the buffer.");
    }

    /** Consumer sleeps for random amount of time (0-2 seconds) */
    public void sleep() {
        System.out.println("Consumer " + getConsumerID() + " is sleeping.");
        try {
            Thread.sleep((int) (Math.random() * 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Execute the following code in the thread */
    @Override
    public void run() {
        while (true) {
            sleep();
            take();
        }
    }
}
