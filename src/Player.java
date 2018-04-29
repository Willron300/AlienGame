import java.util.Scanner;

/**
 * Diese Klasse erzeugt ein Player Objekt. Sie vererbt aus der Character-Klasse.
 * Die Klasse hat vier Klassenvariabeln. Die X- und Y-Koordinate sowie ein Integer fur den Lebenspunkte.
 * Zudem ein Itemliste vom Typ MyList.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Player extends Character {
    /**
     * Beim erzeugen des Player-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem wird das Character Objekt erzeugt mit den passenden Varaiabeln.
     * Danach werden die Trefferwahrscheinlichkeit und die Zielkoordinaten definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    private MyList itemListe = new MyList();

    public Player(int x, int y) {
        super(x, y, 5, 3);
        int trefferWahrscheinlichkeit = 100;
        int[][] ziel = {{0, 0}};
        setTrefferWahrscheinlichkeit(trefferWahrscheinlichkeit);
        setZiel(ziel);
    }

    /**
     * Diese Funktion ist zustaendig für den String der Bewegung des Players. Mithilfe des Scanner Objektes,
     * kann der Benutzer einen String eingeben.
     * @return String       der angegebene String
     */
    @Override
    public String scanMove() {
        System.out.print("Wohin soll der Spieler gehen : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    /**
     * Diese Funktion fuehrt den Angriff des Spieler auf den Alien durch. Ob der Angriff erfolgt hat wird zufaellig
     * entschieden und haengt von der Distanz des Alien vom Spieler ab. Wenn der Alien getroffen wird der Boolean des
     * Aliens auf false gesetzt.
     * @param alien		    Alien-Objekt
     * @param spielfeld		Map-Objekt
     */

    public void angriff(Alien alien, Map spielfeld) {
        int wahrscheinlichkeit = (int) (100.0 - (getTrefferWahrscheinlichkeit()
                * (1.0 - (1.0 / distance(alien.getKoorX(), alien.getKoorY())))));
        int zufall = (int) (Math.random() * 100);

        if (wahrscheinlichkeit > zufall) {
            alien.setLeben(-1);
            System.out.println("Der Spieler hat das Alien getroffen, an Postition " + alien);
        } else {
            System.out.println("Der Spieler hat das Alien verfehlt, an Postition " + alien);
        }

    }

    /**
     * Benutzt das Item an der Stelle i in der Itemliste.
     * @param i     Index des Items in der Liste.
     */
    public void useItem(int i) {
        Item a = (Item)getItemListe().getItem(i);
        getItemListe().delete(a);
        setLeben(a.getHeal());
    }

    /**
     * Get-Methode fuer die Itemliste
     * @return MyList
     */
    public MyList getItemListe() {
        return itemListe;
    }

    /**
     * Fuegt ein Item in die Liste ein
     * @param a Item
     */
    public void setItemListe(Item a) {
        this.itemListe.sortedInsert(a);
    }
}
