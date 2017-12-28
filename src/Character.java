/**
 * Diese Klasse ist die Grundklasse von der Alien und Player-Klasse. Sie enthaelt x und y-Koordinaten und die
 * distance-Funktion.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Character {
    private int leben;
    private int koorX;
    private int koorY;
    private int[][] ziel;
    private int trefferWahrscheinlichkeit;
    private Interface bewegung;

    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Character(int x, int y, int leben) {
        this.leben = leben;
        this.koorX = x;
        this.koorY = y;
    }

    /**
     * Diese Funktion berechnet die Distanz zwischen Alien und Spieler und gibt den Wert zurueck.
     * @param x		X-Koordinate zum bestimmten Objekt
     * @param y		Y-Koordinate zum bestimmten Objekt
     * @return  Ein Integer welcher die Distanz von Alien zu Spieler enthalt
     */
    public double distance(int x, int y) {
        double distance;
        distance = Math.abs(getKoorX() - x) + Math.abs(getKoorY() - y);

        return distance;
    }
    /**
     * Zum Aendern der Ziel-Variable
     * @param ziel  Liste von Integers der Zielkoordinaten
     */
    public void setZiel(int[][] ziel) {
        this.ziel = ziel;
    }
    /**
     * Abfrage der Ziel-Variable.
     * @return Liste von Integer der Ziel Koordinaten.
     */
    public int[][] getZiel() {
        return ziel;
    }
    /**
     * Abfrage der Trefferwahrscheinlichkeit-Variable.
     * @return Integer der Trefferwahrscheinlichkeit.
     */
    public int getTrefferWahrscheinlichkeit() {
        return trefferWahrscheinlichkeit;
    }
    /**
     * Zum Aendern der Trefferwahrscheinlichkeit-Variable
     * @param trefferWahrscheinlichkeit  Integer der neuen Trefferwahrscheinlichkeit
     */
    public void setTrefferWahrscheinlichkeit(int trefferWahrscheinlichkeit) {
        this.trefferWahrscheinlichkeit = trefferWahrscheinlichkeit;
    }
    /**
     * Abfrage der X-Koordinate.
     * @return Integer der X-Koordinate.
     */
    public int getKoorX() {
        return koorX;
    }
    /**
     * Abfrage der Y-Koordinate.
     * @return Integer der Y-Koordinate.
     */
    public int getKoorY() {
        return koorY;
    }
    /**
     * Die Funktion ueberschreibt die toString Methode und erzeugt ein String mit den Koordinaten.
     * return str       Die Koordinaten des Spielers als String
     */
    @Override
    public String toString() {
        String str = "(" + koorY + "," + koorX + ")";
        return str;
    }
    /**
     * Abfrage der Lebenstatus-Variable.
     * @return Integer des Lebenstatus.
     */
    public int getLeben() {
        return leben;
    }
    /**
     * Zum Aendern der Lebenstatus-Variable. Es wird ein Leben abgezogen.
     */
    public void setLeben() {
        this.leben--;
    }

    public void setKoorX(int koorX) {
        this.koorX = koorX;
    }

    public void setKoorY(int koorY) {
        this.koorY = koorY;
    }
    public int[] getKoor() {
        return new int[] {koorX,koorY};
    }

    public void setBewegung(int breite, int laenge) {
        this.bewegung = new Interface(this, breite, laenge);
    }

    public Interface getBewegung() {
        return bewegung;
    }
}
