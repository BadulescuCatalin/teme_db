public class Main {
    public static void main(String[] args) {
        // coada comuna la care au acces atat producerii cat si consumarul
        // SynchronousQueue este o clasa facuta de mine ce simuleaza o coada sincronizata de lungime maxSize
        SynchronizedQueue synchronizedQueue = new SynchronizedQueue(5);

        // un vector comun si la producer si la consumer care ma anunta cand au terminat toti producerii, deci
        // se poate opri si consumerul
        boolean[] isProducerFinished = new boolean[3];

        // initializez consumerul si producerii
        Thread[] threads = new Thread[4];
        threads[0] = new Thread(new Consumer(synchronizedQueue, isProducerFinished));
        threads[1] = new Thread(new Producer(synchronizedQueue, 0, isProducerFinished));
        threads[2] = new Thread(new Producer(synchronizedQueue, 1, isProducerFinished));
        threads[3] = new Thread(new Producer(synchronizedQueue, 2, isProducerFinished));

        // start si join la thread-uri
        for (int i = 0; i < 4; ++i) {
            threads[i].start();
        }
        for (int i = 0; i < 4; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
