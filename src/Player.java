/**
 * Diese Klasse erzeugt ein Player Objekt. Die Klasse hat drei Klassenvariabeln. Die X- und Y-Koordinate sowie ein
 * Integer fur den Lebenspunkte.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Player {
    int koor_x;
    int koor_y;
    int leben = 5;
    /**
     * Beim erzeugen des Player-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Player(int x, int y) {
        this.koor_x = x;
        this.koor_y = y;
    }
    /**
     * Diese Funktion fuehrt den Angriff des Spieler auf den Alien durch. Ob der Angriff erfolgt hat wird zufaellig
     * entschieden und haengt von der Distanz des Alien vom Spieler ab. Wenn der Alien getroffen wird der Boolean des
     * Aliens auf false gesetzt
     * @param alien		    Alien-Objekt
     * @param spielfeld		Map-Objekt
     */
    public void angriff(Alien alien, Map spielfeld) {
        int wahrscheinlichkeit = (int) (100.0 - (100.0 * (1.0 - (1.0 / distance(alien)))));
        int zufall = (int) (Math.random() * 100);

        if (wahrscheinlichkeit > zufall) {
            System.out.println(" ; rein");
            alien.leben = false;
            spielfeld.spielfeld[alien.koor_x][alien.koor_y] = 'X';
            System.out.println("Der Spieler hat das Alien getroffen, an Postition " + alien);
        } else {
            System.out.println("Der Spieler hat das Alien verfehlt, an Postition " + alien);
        }

    }
    /**
     * Die Funktion ueberschreibt die toString Methode und erzeugt ein String mit den Koordinaten.
     * return str       Die Koordinaten des Alien als String
     */
    @Override
    public String toString() {
        String str = "(" + koor_y + "," + koor_x + ")";
        return str;
    }
    /**
     * Diese Funktion berechnet die Distanz zwischen Alien und Spieler und gibt den Wert zurueck.
     * @param alien		Alien-Objekt
     * @return  Ein Integer welcher die Distanz von Alien zu Spieler enthalt
     */
    public double distance(Alien alien) {
        double distance;
        distance = Math.abs(koor_x - alien.koor_x) + Math.abs(koor_y - alien.koor_y);

        return distance;
    }
}
