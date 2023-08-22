import java.util.Random;

/**
 * clasa ce reprezinta un Consumer, adica imprimanta;
 * SynchronizedQueue este coada comuna si sincronizata;
 * isProducerFinished vector de boolean care imi zice daca thread-ul isProducerFinished[i] a terminat de produs
 * aici o sa il folosesc ca sa stiu cand ma opresc din consumat;
 */

public class Consumer implements Runnable {
    private SynchronizedQueue queue;
    private boolean[] isProducerFinished;

    public Consumer(SynchronizedQueue queue, boolean[] isProducerFinished) {
        this.queue = queue;
        this.isProducerFinished = isProducerFinished;
    }

    @Override
    public void run() {
        Random random = new Random();
        // cat timp nu au terminat producerii sau coada nu e goala
        while (!shouldStop() || !queue.isEmpty()) {
            // timpul de printare a documentului
            int printTime = random.nextInt(800);
            try {
                // "printez" documentul
                String fileName = queue.pop();
                Thread.sleep(printTime);
                System.out.println("S-a printat documentul: " + fileName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // funtie care verifica daca toti producerii au terminat de produs
    public boolean shouldStop() {
        for (boolean producer : isProducerFinished) {
            if (!producer) {
                return false;
            }
        }
        return true;
    }
}
