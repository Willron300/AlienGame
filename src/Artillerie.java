/**
 * Diese Klasse erzeugt ein Artillerie-Objekt. Sie vererbt aus der Player-Klasse.
 * Das Artillerie Objekt hat eine schlechte Trefferwahrscheinlichkeit aber dafür greift zusatzlich alle Felder
 * darum an.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Artillerie extends Player {
    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem werden die Trefferwahrscheinlichkeit und die Zielkoordinaten definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Artillerie(int x, int y) {
        super(x, y);
        int trefferWahrscheinlichkeit = 120;
        int[][] ziel = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        setTrefferWahrscheinlichkeit(trefferWahrscheinlichkeit);
        setZiel(ziel);
    }
}

