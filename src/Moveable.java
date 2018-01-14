/**
 * Dieses Interface enthalt die abstrakten Methoden, welche im Bewegungs-Objekt enthalten sein muessen.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public interface Moveable {
    /**
     * Diese abstrakte Methode soll den Charakter bewegen lassen.
     * @param bewegungString	    der String fuer die Bewegung
     * @param spielfeld		        das Map-Objekt
     */
    public void move(String bewegungString, Map spielfeld);
    /**
     * Diese abstrakte Methode soll den ueberpruefen ob der String der Bewegung keine Fehler enthaelt.
     * @param bewegungString	    der String fuer die Bewegung
     * @param spielfeld		        das Map-Objekt
     * @return boolean              Ob alles i.O. oder nicht.
     */
    public boolean canMove(String bewegungString, Map spielfeld);
}