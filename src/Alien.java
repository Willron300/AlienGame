/**
 * Diese Klasse erzeugt ein Alien Objekt. Sie vererbt aus der Character-Klasse.
 * Die Klasse hat 5 Klassenvariabeln. Die X- und Y-Koordinate sowie ein Boolean fur den Lebenstatus.
 * Zudem eine Trefferwahrscheinlichkeit und Zielkoordinaten fuer einen Angriff.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Alien extends Character {
    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem werden die Trefferwahrscheinlichkeit und die Zielkoordinaten definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Alien(int x, int y) {
        super(x, y, 1);
        int trefferWahrscheinlichkeit = 100;
        int[][] ziel = {{0, 0}};
        setTrefferWahrscheinlichkeit(trefferWahrscheinlichkeit);
        setZiel(ziel);
        this.getBewegung().setMaxLange(2);
    }

    /**
     * Diese Funktion fuehrt den Angriff des Alien auf den Spieler durch. Ob der Angriff erfolgt hat wird zufaellig
     * entschieden und haengt von der Distanz des Alien vom Spieler ab. Wenn der Spieler getroffen wird verliert der
     * Spieler ein Leben.
     * @param spieler		Player-Objekt
     */
    public void angriff(Player spieler) {
        if (spieler.getLeben() > 0) {
            int wahrscheinlichkeit = (int) (100.0 - (getTrefferWahrscheinlichkeit()
                    * (1.0 - (1.0 / distance(spieler.getKoorX(), spieler.getKoorY())))));
            int zufall = (int) (Math.random() * 100);
            if (wahrscheinlichkeit > zufall) {
                spieler.setLeben();
                System.out.println("Der Alien (" + getKoorY() + "," + getKoorX() + ") hat den Spieler getroffen");
            } else {
                System.out.println("Der Alien (" + getKoorY() + "," + getKoorX() + ") hat das Spieler verfehlt");
            }
        }
    }
}
