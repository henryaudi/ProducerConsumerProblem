public class Main {
    public static void main(String[] args) {
        // Define the size of the buffer.
        final int BUFFER_SIZE = 5;     // The number of buffers
        final int CONSUMER_NUM = 5;    // The number of consumers
        final int PRODUCER_NUM = 2;    // The number of producers

        // Initialize the buffer.
        Buffer buffer = new Buffer(BUFFER_SIZE);

        // Instantiate the Consumer-Producer class object and load the program to threads.
        new ConsumerProducer(buffer, CONSUMER_NUM, PRODUCER_NUM);
    }
}
