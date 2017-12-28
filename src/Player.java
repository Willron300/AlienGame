/**
 * Diese Klasse erzeugt ein Player Objekt. Sie vererbt aus der Character-Klasse.
 * Die Klasse hat drei Klassenvariabeln. Die X- und Y-Koordinate sowie ein Integer fur den Lebenspunkte.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Player extends Character {
    /**
     * Beim erzeugen des Player-Objektes werden die Klassenvariabeln neu definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Player(int x, int y) {
        super(x, y, 5);
        this.getBewegung().setMaxLange(3);
    }

    /**
     * Diese Funktion fuehrt den Angriff des Spieler auf den Alien durch. Ob der Angriff erfolgt hat wird zufaellig
     * entschieden und haengt von der Distanz des Alien vom Spieler ab. Wenn der Alien getroffen wird der Boolean des
     * Aliens auf false gesetzt.
     * @param alien		    Alien-Objekt
     * @param spielfeld		Map-Objekt
     */

    public void angriff(Alien alien, Map spielfeld) {
        int wahrscheinlichkeit = (int) (100.0 - (getTrefferWahrscheinlichkeit() * (1.0 - (1.0 / distance(alien.getKoorX(), alien.getKoorY())))));
        int zufall = (int) (Math.random() * 100);

        if (wahrscheinlichkeit > zufall) {
            alien.setLeben();
            System.out.println("Der Spieler hat das Alien getroffen, an Postition " + alien);
        } else {
            System.out.println("Der Spieler hat das Alien verfehlt, an Postition " + alien);
        }

    }

}
