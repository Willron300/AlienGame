/**
 * Diese Klasse erzeugt ein Scharfschuetzen-Objekt. Sie vererbt aus der Player-Klasse.
 * Das Scharfschuetzen Objekt hat eine bessere Trefferwahrscheinlichkeit aber nur eine Angriffskoordinate.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Scharf extends Player {
    /**
     * Beim erzeugen des Scharfschuetzen-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem werden die Trefferwahrscheinlichkeit und die Zielkoordinaten definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Scharf(int x, int y) {
        super(x, y);
        int trefferWahrscheinlichkeit = 80;
        int[][] ziel = {{0, 0}};
        setTrefferWahrscheinlichkeit(trefferWahrscheinlichkeit);
        setZiel(ziel);
    }
}
