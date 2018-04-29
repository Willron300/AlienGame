/**
 * Diese Klasse erzeugt ein Item Objekt.
 * Die Klasse hat eine Klassenvariabeln. Ein Integer Wert als Heilwert.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Item implements Comparable {
    private int heal;

    /**
     * Konstruktor
     * Der Wert des Heals wird zufallig bestimmt.
     */
    public Item() {
        this.heal = (int) ((Math.random() * 5) + 1);
    }

    /**
     * Die Funktion vergleicht zwei Item Objekte auf Gleichheit.
     *
     * @param i Das zu vergleichende Item-Objekt
     * @return true wenn der Heal Wert mit dem des anderen Objektes uebereinstimmt
     */
    public boolean equals(Item i) {
        if (i.getHeal() == getHeal()) {
            return true;
        }
        return false;
    }

    /**
     * Get-Methode des Heal.
     * @return den Integerwert der Healvariable
     */
    public int getHeal() {
        return heal;
    }

    /**
     * Ueberschreibt die String Methode
     * @return String Der Healwert
     */
    @Override
    public String toString() {
        return String.valueOf(heal);
    }

    /**
     * Ueberschreibt die compareTo-Methode.
     * Methode zum vergleichen der Item-Objekte.
     * @param o  Item-Objekt
     * @return      Einen Wert welcher den abhaengig von den zuvergleichenden Objekten ist.
     */
    @Override
    public int compareTo(Object o) {
        Item a = (Item) o;
        return a.getHeal() - getHeal();
    }
}
