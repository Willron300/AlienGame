/**
 * Diese Klasse erzeugt ein Alien Objekt. Die Klasse hat drei Klassenvariabeln. Die X- und Y-Koordinate sowie ein
 * Boolean fur den Lebenstatus.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Alien {
    int koor_x;
    int koor_y;
    Boolean leben = true;
    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Alien(int x, int y) {
        this.koor_x = x;
        this.koor_y = y;
    }
    /**
     * Diese Funktion fuehrt den Angriff des Alien auf den Spieler durch. Ob der Angriff erfolgt hat wird zufaellig
     * entschieden und haengt von der Distanz des Alien vom Spieler ab. Wenn der Spieler getroffen wird verliert der
     * Spieler ein Leben.
     * @param spieler		Player-Objekt
     * @param spielfeld		Map-Objekt
     */
    public void angriff(Player spieler, Map spielfeld) {
        if (spieler.leben > 0) {
            int wahrscheinlichkeit = (int) (100.0 - (100.0* (1.0-(1.0/distance(spieler)))));
            int zufall = (int) (Math.random() * 100);
            if(wahrscheinlichkeit > zufall) {
                spieler.leben--;
                System.out.println("Der Alien (" + koor_y + "," + koor_x + ") hat den Spieler getroffen");
            }else{
                System.out.println("Der Alien (" + koor_y + "," + koor_x + ") hat das Spieler verfehlt");
            }
        }
    }
    /**
     * Diese Funktion berechnet die Distanz zwischen Alien und Spieler und gibt den Wert zurueck.
     * @param spieler		Player-Objekt
     * @return  Ein Integer welcher die Distanz von Alien zu Spieler enthalt
     */
    public int distance(Player spieler) {
        int distance;
        distance = Math.abs(koor_x - spieler.koor_x) + Math.abs(koor_y - spieler.koor_y);

        return distance;
    }
    /**
     * Die Funktion ueberschreibt die toString Methode und erzeugt ein String mit den Koordinaten.
     * return str       Die Koordinaten des Spielers als String
     */
    @Override
    public String toString() {
        String str = "(" + koor_y + "," + koor_x + ")";
        return str;
    }

}
