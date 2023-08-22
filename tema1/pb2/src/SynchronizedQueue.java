import java.util.LinkedList;
import java.util.Queue;

/**
 * clasa care reprezinta coada comuna si sincronizata;
 * maxSize este dimensiunea maxima a cozii;
 * synvhronizedQueue este coada efectiva;
 */

public class SynchronizedQueue {
    private int maxSize;
    private Queue<String> synchronizedQueue;

    public SynchronizedQueue(int maxSize) {
        this.maxSize = maxSize;
        this.synchronizedQueue = new LinkedList<>();
    }

    // functia de adaugat in coada
    // doar un producer are voie sa adauge la un moment dat
    // doar un push sau un pop au voie sa fie facute la un moment dat (folosesc acelasi monitor pt ca am coada comuna)
    public synchronized void push(String fileName) throws InterruptedException {
        // cat timp coada este plina astept, apoi adaug si notific
        while (synchronizedQueue.size() == maxSize) {
            wait();
        }
        synchronizedQueue.add(fileName);
        notifyAll();
    }

    // functia de scos din coada
    // doar un consumer are voie sa scoata din coada la un moment dat ( am doar 1 )
    // doar un push sau un pop au voie sa fie facute la un moment dat (folosesc acelasi monitor pt ca am coada comuna)
    public synchronized String pop() throws InterruptedException {
        // cat timp nu am ce sa scot din coada, astept apoi dau remove si norific
        while (synchronizedQueue.size() == 0) {
            wait();
        }
        String fileName = synchronizedQueue.poll();
        notifyAll();
        return fileName;
    }

    // functia care imi zice daca coada e goala
    public synchronized boolean isEmpty() {
        return synchronizedQueue.isEmpty();
    }
}
