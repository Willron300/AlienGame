import java.util.Random;

/**
 * Diese Klasse erzeugt ein Alien Objekt. Sie vererbt aus der Character-Klasse.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Alien extends Character {
    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem wird das Character Objekt erzeugt mit den passenden Varaiabeln.
     * Danach werden die Trefferwahrscheinlichkeit und die Zielkoordinaten definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Alien(int x, int y) {
        super(x, y, 1, 2);
        int trefferWahrscheinlichkeit = 100;
        int[][] ziel = {{0, 0}};
        setTrefferWahrscheinlichkeit(trefferWahrscheinlichkeit);
        setZiel(ziel);
    }
    /**
     * Diese Funktion ist zustaendig für den String der Bewegung des Aliens. Sie erzeugt einen zufaelligen String,
     * aus den Elementen a, s, d, w.
     * @return String       der zufaellig erzeugte String
     */
    @Override
    public String scanMove() {
        StringBuilder moveString = new StringBuilder("");
        char[] richtungen = new char[] {'a', 's', 'd', 'w'};

        int schritte = (int) (Math.random() * (getBewegungsMax() + 1));
        for (int i = 0; i < schritte; i++) {
            int rnd = new Random().nextInt(richtungen.length);
            moveString.append(richtungen[rnd]);
        }

        return moveString.toString();
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
                System.out.printf("Der Alien %s hat den Spieler getroffen! \n", toString());
            } else {
                System.out.printf("Der Alien %s hat das Spieler verfehlt!\n", toString());
            }
        }
    }
}
