public class ConsumerProducer {
    // Data Fields
    private Producer[] producers;
    private  Consumer[] consumers;
    private Buffer buffer;
    private Thread[] producerThreads;
    private Thread[] consumerThreads;

    // Constructor
    public ConsumerProducer(Buffer buffer, int numConsumers, int numProducers) {
        this.buffer = buffer;
        this.consumers = new Consumer[numConsumers];
        this.consumerThreads = new Thread[numConsumers];
        this.producers = new Producer[numProducers];
        this.producerThreads = new Thread[numProducers];

        // Add new consumers to the consumers array.
        for (int i = 0; i < numConsumers; i++) {
            Consumer consumer = new Consumer(buffer, i);
            consumers[i] = consumer;
        }

        // Add new producers to the producer array.
        for (int i = 0; i < numProducers; i++) {
            Producer producer = new Producer(buffer, i);
            producers[i] = producer;
        }

        // Load the producers to threads.
        for (int i = 0; i < numProducers; i++) {
            producerThreads[i] = new Thread(producers[i]);
            producerThreads[i].start();
        }

        // Load the consumers to threads.
        for (int i = 0; i < numConsumers; i++) {
            consumerThreads[i] = new Thread(consumers[i]);
            consumerThreads[i].start();
        }

        // Terminate the consumer threads.
        for (Thread t : consumerThreads) {
            try {
                t.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Terminate the producer threads.
        for (Thread t : producerThreads) {
            try {
                t.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
