import java.util.Random;

/**
 * clasa ce reprezinta un producer;
 * SynchronizedQueue este coada comuna si sincronizata;
 * producerId este id-ul thread-ului. Il folosesc cand afisez;
 * isProducerFinished vector de boolean care imi zice daca thread-ul isProducerFinished[i] a terminat de produs
 * aici o sa il folosesc doar ca sa marchez ca threadId a terminat de produs;
 */

public class Producer implements Runnable {
    private SynchronizedQueue queue;
    private int producerId;
    private boolean[] isProducerFinished;

    public Producer(SynchronizedQueue queue, int producerId, boolean[] isProducerFinished) {
        this.queue = queue;
        this.producerId = producerId;
        this.isProducerFinished = isProducerFinished;
    }

    @Override
    public void run() {
        // simulez generarea a 30 de fisiere pentru imprimanta
        for (int i = 0; i < 30; ++i) {
            Random random = new Random();
            // timpul de generare a fisierului
            int productionTime = random.nextInt(2000);
            // numele fisierului generat
            String fileName = "Document " + i + " produs de catre producer " + producerId;
            try {
                // "generez" fisierul si il adaug in coada comuna ( a imprimantei)
                Thread.sleep(productionTime);
                queue.push(fileName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // marchez ca producerul producerId a terminat de produs
        isProducerFinished[producerId] = true;
    }
}
