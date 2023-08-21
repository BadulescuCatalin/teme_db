/**
 * exceptia care apare cand indexul de la care incerc sa sterg nu este in limitele [0, students.size()]
 */
public class CustomIndexOutOfBoundException extends Exception {
    CustomIndexOutOfBoundException(int size, int index) {
        super("Indexul introdus: " + index + " este invalid. Introduceti un index din intervalul: [0, " + size + "]");
    }
}
