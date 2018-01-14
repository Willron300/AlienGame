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
    private Bewegung bewegung;
    private int bewegungsMax;

    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		        x-Koordinate
     * @param y		        y-Koordinate
     * @param leben		    Leben das Charakter
     * @param bewegungsMax  Maximale Bewegungsschritte des Charakters
     */
    public Character(int x, int y, int leben, int bewegungsMax) {
        this.leben = leben;
        this.koorX = x;
        this.koorY = y;
        this.bewegungsMax = bewegungsMax;
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
        String str = "(" + koorX + "," + koorY + ")";
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
    /**
     * Zum Aendern der der X-Koordinate
     * @param koorX   neue X-Koordinate
     */
    public void setKoorX(int koorX) {
        this.koorX = koorX;
    }
    /**
     * Zum Aendern der der Y-Koordinate
     * @param koorY   neue Y-Koordinate
     */
    public void setKoorY(int koorY) {
        this.koorY = koorY;
    }
    /**
     * Abfrage der Koordinaten
     * @return int[] der Koordinaten
     */
    public int[] getKoor() {
        return new int[] {koorX, koorY};
    }
    /**
     * Erzeugt ein Bewegungs-Objekt welches sich um die Bewegung des Charakters kuemmert
     * @param breite   Breite des Spielfeldes
     * @param laenge   Laenge des Spielfeldes
     */
    public void setBewegung(int breite, int laenge) {
        this.bewegung = new Bewegung(this, breite, laenge);
    }
    /**
     * Abfrage des Bewegungs-Objektes.
     * @return  Bewegung bewegung      das Bewegungs-Objekt
     */
    public Bewegung getBewegung() {
        return bewegung;
    }
    /**
     * Abfrage der maximalen Bewegungsreichweite des Charakters
     * @return  int bewegungsMax      die Bewegungsreichweite
     */
    public int getBewegungsMax() {
        return bewegungsMax;
    }
    /**
     * Abstrakte Methode
     * @return  null
     */
    public String scanMove() {
        return null;
    }
}
