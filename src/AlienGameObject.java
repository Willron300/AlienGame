/**
 * Diese Klasse ist die Grundklasse von AlienGame-Objekten. Sie enthaelt die X- und Y- Koordinaten.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class AlienGameObject {
    private int koorX;
    private int koorY;
    /**
     * Beim erzeugen des AlienGameObject-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		        x-Koordinate
     * @param y		        y-Koordinate
     */
    public AlienGameObject(int x, int y) {
        this.koorX = x;
        this.koorY = y;
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
}
