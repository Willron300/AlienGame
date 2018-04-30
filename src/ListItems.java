import java.util.Iterator;

/**
 * Dieses Interface enthalt die abstrakten Methoden, welche om MyList benoetigt werden.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public interface ListItems {

    /**
     * Ueberpruft of Liste leer ist.
     */
    boolean isEmpty();

    /**
     * Gibt die Laenge der Liste zurueck
     * @return int Laenge der Liste.
     */
    int length();

    /**
     * Gibt das Erste Objekt in der Liste zurueck.
     * @return erste Objekt in der Liste
     */
    Object first();

    /**
     *  Fuegt das Objekt am Anfang der List ein.
     * @param e  das einzufuegende Objekt
     */
    void insert(Object e);

    /**
     *  Fuegt das Objekt am Ende der List ein.
     * @param e  das einzufuegende Objekt
     */
    void append(Object e);

    /**
     *  Fuegt das Objekt sortiert in der List ein.
     * @param e  das einzufuegende Objekt
     */
    void sortedInsert(Object e);

    /**
     * Loescht das Objekt aus der Liste.
     * @param e das zu loeschende Objekt
     */
    void delete(Object e);

    /**
     * ???
     */
    Iterator iterator();

    /**
     * Gibt das Objekt mit dem Index i zurueck
     * @param i   Index
     * @return Object
     */
    Object getItem(int i);
}
