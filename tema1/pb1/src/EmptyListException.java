/**
 * exceptia care apare cand lista de studenti e goala, iar eu incerc sa sterg
 */
public class EmptyListException extends Exception {
    EmptyListException() {
        super("Lista de studenti este goala. Nu se poate sterge dintr-o lista goala!");
    }
}
