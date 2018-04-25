import java.util.Iterator;

/**
 * Dieses Interface enthalt die abstrakten Methoden, welche om MyList benoetigt werden.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public interface ListItems {
    /**
     * Diese abstrakte Methode soll den Charakter bewegen lassen.
     */
    boolean isEmpty();
    /**
     * Diese abstrakte Methode soll den ueberpruefen ob der String der Bewegung keine Fehler enthaelt.
     * @return boolean              Ob alles i.O. oder nicht.
     */
    int length();
    Object first(Object e);
    void insert(Object e);
    void append(Object e);
    void sortedInsert(Object e);
    void delete(Object e);
    void iterator();
    Object getItem();
}
