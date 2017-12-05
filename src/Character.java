/**
 * Diese Klasse ist die Grundklasse von der Alien und Player-Klasse. Sie enthaelt x und y-Koordinaten und die
 * distance-Funktion.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */

public class Character {
    private int koorX;
    private int koorY;
    private int TrefferWahrscheinlichkeit;
    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Character(int x, int y) {
        this.koorX = x;
        this.koorY = y;
    }

    /**
     * Die Funktion ueberschreibt die toString Methode und erzeugt ein String mit den Koordinaten.
     * return str       Die Koordinaten des Character-Objekt als String
     */
    @Override
    public String toString() {
        String str = "(" + koorY + "," + koorX + ")";
        return str;
    }
    /**
     *public int distance(Player spieler) {
     *int distance;
     *distance = Math.abs(koor_x - spieler.koor_x) + Math.abs(koor_y - spieler.koor_y);

     *return distance;
     *}
     */

}
