import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Diese Klasse erzeugt ein Spielfeld vom Typ Character[][] und ein Player-Objekt und eine Liste von Alien-Objekten.
 * Es wird ein Player Objekt erzeugt und eine Liste von Aliens, dann gibt es eine Funktion welche das Character
 * Array immer aktualiesiert mit den neuen Koordinaten der Character Objekte.
 * Die Klasse hat drei Klassenavariabeln: spielfeld, spieler und aliens.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Map {
    private Character[][] spielfeld;
    private Player spieler;
    private Alien[] aliens;
    private int spielerTyp;
    /**
     * Dann wird ein Spielfeld vom Type Character[][] erzeugt.
     * Danach wird diesen Array mit bestimmten Objekten befuellt.
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @param spielerTyp	    Spielertyp des Spielers als Integer.
     */
    public Map(int breite, int laenge, int anzahlAliens, int spielerTyp) {
        this.aliens = new Alien[anzahlAliens];
        this.spielfeld = new Character[breite][laenge];
        this.spielerTyp = spielerTyp;

        this.spielfeld = besetzeSpielfeld(anzahlAliens, breite, laenge);

    }
    public AlienGameObject[][] generateMatchfield(int breite, int laenge) {
        AlienGameObject[][] irrgarten = new AlienGameObject[breite][laenge];
        irrgarten = besetzeWall(irrgarten);
        int x = (int) (Math.random() * laenge);
        int y = (int) (Math.random() * breite);
        workIrrgarten(x, y, irrgarten);
        return irrgarten;
    }
    public static void kack(AlienGameObject[][] spielfeld) {
        for (int i = 0; i < spielfeld.length; i++ ) {                   // Jede Zeile wird abgearbeitet
            for (int j = 0; j < spielfeld[i].length; j++) {            // Jede Spalte wird abgearbeitet
                if (spielfeld[i][j] instanceof Wall) {
                    System.out.print(spielfeld[i][j]);
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }

    }
    public void workIrrgarten(int x, int y, AlienGameObject[][] irrgarten) {
        kack(irrgarten);
        if (irrgarten[y][x] instanceof Wall) {
            Wall wall = (Wall) irrgarten[y][x];
            if (!wall.getMark()) {

                wall.setMark(true);
                try {
                    if (irrgarten[y-1][x] instanceof Wall && !(((Wall) irrgarten[y-1][x]).getMark())) {
                        ((Wall) irrgarten[y][x]).setList((Wall) irrgarten[y-1][x]);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    ;
                }
                try {
                    if (irrgarten[y+1][x] instanceof Wall && !(((Wall) irrgarten[y+1][x]).getMark())) {
                        ((Wall) irrgarten[y][x]).setList((Wall) irrgarten[y+1][x]);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    ;
                }
                try {
                    if (irrgarten[y][x-1] instanceof Wall && !(((Wall) irrgarten[y][x-1]).getMark())) {
                        ((Wall) irrgarten[y][x]).setList((Wall) irrgarten[y][x-1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    ;
                }
                try {
                    if (irrgarten[y][x+1] instanceof Wall && !(((Wall) irrgarten[y][x+1]).getMark())) {
                        ((Wall) irrgarten[y][x]).setList((Wall) irrgarten[y][x+1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    ;
                }


                while(wall.checkList()) {
                    System.out.println(wall.getList());
                    int rnd = new Random().nextInt(wall.getList().size());

                    Wall nextWall = wall.getList().get(rnd);
                    if (!nextWall.getMark()) {
                        irrgarten[y][x] = null;
                        workIrrgarten(nextWall.getKoorX(), nextWall.getKoorY(), irrgarten);
                    }
                }
            }


        }

    }
    /**
     * Diese Funktion erhaelt ein Spielfeld und besetzt es mit Leerzeichen.
     * @param irrgarten	 Ein Spielfeld von Type Char[][].
     * @return Ein Char[][] besetzt mit Leerzeichen.
     */
    public static AlienGameObject[][] besetzeWall(AlienGameObject[][] irrgarten) {
        for (int i = 0; i < irrgarten.length; i++) {
            for (int j = 0; j < irrgarten[i].length; j++) {
                Wall wall = new Wall(j,i);
                irrgarten[i][j] = wall;
            }
        }
        return irrgarten;
    }
    /**
     * Diese Funktion erzeugt zunaechst ein Spieler-Objekt und danach eine Liste von Aliens. Dann wird das
     * Character Array mit diesen Objekten befuellt.
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @return Ein Character[][] besetzt mit einer bestimmten Anzahl von Aliens-Objekten und einem Spieler-Objekt.
     */
    public Character[][] besetzeSpielfeld(int anzahlAliens, int breite, int laenge) {
        this.spieler = erzeugeSpieler(breite, laenge);
        this.aliens = erzeugeAlien(spieler, breite, laenge, anzahlAliens);
        aktualisiereSpielfeld();
        return spielfeld;
    }
    /**
     * Diese Funktion aktualisiert das Character Array. Zunaechst wird alles mit null besetzt (resetet).
     * Danach fuellen wir das Array mit den Spielern und den Aliens.
     */
    public void aktualisiereSpielfeld() {
        this.spielfeld = besetzeLeerzeichen(spielfeld);
        this.spielfeld[spieler.getKoorY()][spieler.getKoorX()] = spieler;
        for (Alien alien: aliens) {
            this.spielfeld[alien.getKoorY()][alien.getKoorX()] = alien;
        }
    }
    /**
     * Diese Funktion erzeugt die Aliens. Es werden ein bestimmte Anzahl von Aliens erzeugt werden und dementsprechend
     * viele unterschiedliche Koordinaten.
     *
     * @param spieler		    Spieler-Objekt
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @param anzahlAliens		Anzahl der Aliens
     * @return Alien[] besetzt mit einer bestimmten Anzahl von Aliens-Objekten.
     */
    public Alien[] erzeugeAlien(Player spieler, int breite, int laenge, int anzahlAliens) {
        Alien[]alienList = new Alien[anzahlAliens];
        int[][] koordi = new int[anzahlAliens][2];
        Arrays.fill(koordi, null);
        for (int i = 0; i < anzahlAliens; i++) {
            int x = (int) (Math.random() * laenge);        // Erzeuge zufaellige x-Koordinate
            int y = (int) (Math.random() * breite);     // Erzeuge zufaellige y-Koordinate
			/*
				Die While-Schleife ueperprueft ob die Koordinaten des
				Spielfeldes schon mit einem Alien oder Spieler besetzt
				sind, ansonsten erzeugt sie solange neue Koordinaten,
				bis er einen leeren Platz findet.
			 */
            int[] koor = new int[]{x, y};
            while (contain(koordi, koor) || Arrays.equals(spieler.getKoor(), koor)) {
                x = (int) (Math.random() * laenge);
                y = (int) (Math.random() * breite);
                koor = new int[]{x, y};
            }
            koordi[i] = koor;
            Alien alien = new Alien(x, y);                      // Erzeuge Alien-Objekt
            alien.setBewegung(breite, laenge);                  // Bewegungs-Objekt wird im ALien implementiert
            alienList[i] = alien;
        }
        return alienList;
    }
    /**
     * Diese Funktion erzeugt das Spieler Objekt.
     *
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @return Spieler-Objekt.
     */
    public Player erzeugeSpieler(int breite, int laenge) {
        int xKoordSpieler = (int) (Math.random() * laenge);		// Erzeuge zufaellige x-Koordinate
        int yKoordSpieler = (int) (Math.random() * breite);     // Erzeuge zufaellige y-Koordinate
        switch (spielerTyp) {                                   // Fragt den Spielertyp ab
            case 0:
                spieler = new Artillerie(xKoordSpieler, yKoordSpieler);     // Erzeugt Artillerie Objekt
                break;
            case 1:
                spieler = new Scharf(xKoordSpieler, yKoordSpieler);         // Erzeugt Scharfschuetzen Objekt
                break;
            case 2:
                spieler = new Laserstrahler(xKoordSpieler, yKoordSpieler);  // Erzeugt Laserstrahl Objekt
                break;
            default:
                System.out.println("Fehler mit dem Spielertyp");
                break;
        }
        spieler.setBewegung(breite, laenge);                           // Bewegungs-Objekt wird im ALien implementiert
        return spieler;
    }

    /**
     * Die Funktion ueberschreibt die toString Methode und wandelt das Char[][] in einem String um.
     * return str       Das Spielfeld(char[][]) wird dargestellt als String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Spielfeld: \n   ");      // Erzeuge ein Stringbuilder Objekt
        StringBuilder firstRow = new StringBuilder("   ");
        for (int k = 0; k < spielfeld[0].length; k++) {
            if (k % 10 == 0) {
                str.append(k / 10);                                       // Fuege den Zehner-Zahl hinzu
            } else {
                str.append(" ");
            }
            firstRow.append(k % 10);                                    // Fuege den Einzer-Zahl hinzu
        }

        str.append("\n");
        str.append(firstRow.toString());
        str.append("\n ");
        str.append(" " + repeat(spielfeld[0].length + 2, "*"));
        str.append("\n");

        for (int i = 0; i < spielfeld.length; i++ ) {                   // Jede Zeile wird abgearbeitet
            if (i % 10 == 0) {
                str.append(i / 10);                                       // Fuege den Zehner-Zahl hinzu
            } else {
                str.append(" ");
            }
            str.append(i % 10 + "*");                                   // Fuege den Einzer-Zahl hinzu
            for (int j = 0; j < spielfeld[i].length; j++) {            // Jede Spalte wird abgearbeitet
                if (spielfeld[i][j] instanceof Character) {
                    if (spielfeld[i][j] instanceof Player) {
                        str.append("P");
                    }
                    if (spielfeld[i][j] instanceof Alien) {
                        if (spielfeld[i][j].getLeben() == 1) {
                            str.append("A");
                        } else {
                            str.append("X");
                        }
                    }
                } else {
                    str.append(" ");
                }
            }
            str.append("*\n");
        }
        str.append("  " + repeat(spielfeld[0].length + 2, "*"));
        return str.toString();
    }
    /**
     * Diese Funktion erzeugt einen String. Ein String wir x-Mal wiederholt.
     * @param count	 Anzahl der Wiederholungen
     * @param with	 String der wiederholt werden soll.
     * @return String; Der neu erzeugte lange String
     */
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }
    /**
     * Diese Funktion erhaelt ein Spielfeld und besetzt es mit Leerzeichen.
     * @param spielfeld	 Ein Spielfeld von Type Char[][].
     * @return Ein Char[][] besetzt mit Leerzeichen.
     */
    public static Character[][] besetzeLeerzeichen(Character[][] spielfeld) {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                spielfeld[i][j] = null;
            }
        }
        return spielfeld;
    }
    /**
     * Diese Funktion uberprueft ob ein int[] in einem int[][] enthalten ist.
     * @param arr	 int[][]
     * @param item	 int[]
     * @return boolean   True wenn item in arr enthalten ist ansonsten false
     */
    public static boolean contain(int[][] arr, int[] item) {
        for (int[] n : arr) {
            if (Arrays.equals(item, n)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Abfrage der Spieler-Variable.
     * @return Spieler Objekt.
     */
    public Player getSpieler() {
        return spieler;
    }
    /**
     * Zum Aendern der Spieler-Variable
     * @param spieler  Das Spieler-Objekt
     */
    public void setSpieler(Player spieler) {
        this.spieler = spieler;
    }
    /**
     * Abfrage der Alien-Variable.
     * @return  Liste von Aliens Objekten.
     */
    public Alien[] getAliens() {
        return aliens;
    }
    /**
     * Zum Aendern der Liste von Alien-Objekten.
     * @param aliens  Liste von Alien-Objekten.
     */
    public void setAliens(Alien[] aliens) {
        this.aliens = aliens;
    }
    /**
     * Abfrage der Spielfeld-Variable.
     * @return  Das Spielfeld vom Typ char[][].
     */
    public Character[][] getSpielfeld() {
        return spielfeld;
    }
    /**
     * Zum Aendern der Liste von Alien-Objekten.
     * @param charakter  Neuer Wert als Character-Objekt des Spielfeld an den Koordinaten X und Y.
     * @param x  Integer als X-Koordinate
     * @param y  Integer als Y-Koordinate.
     */
    public void setSpielfeld(Character charakter, int x, int y) {
        this.spielfeld[x][y] = charakter;
    }
}