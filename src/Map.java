/**
 * Diese Klasse erzeugt ein Spielfeld vom Typ char[][] und ein Player-Objekt und eine Liste von Alien-Objekten.
 * Das Spielfeld wird mit Leerzeichen sowie P und A bestetzt und das entsprechende Objekt mit den passenden
 * Koordinaten erzeugt.
 * Die Klasse hat drei Klassenavariabeln: spielfeld, spieler und aliens.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Map {
    private char[][] spielfeld;
    private Player spieler;
    private Alien[] aliens;
    private int spielerTyp;
    /**
     * Dann wird ein Spielfeld vom Type Char[][] erzeugt und mit Leerzeichen besetzt.
     * Danach wird das Spielfeld zufaellig mit einen Spieler und einer gewissen Anzahl von Aliens besetzt.
     * Wobei auch das Player-Objekt und die Liste von Alien-Objekte erzeugt werden.
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @param spielerTyp	    Spielertyp des Spielers als Integer.
     */
    public Map(int breite, int laenge, int anzahlAliens, int spielerTyp) {
        this.aliens = new Alien[anzahlAliens];
        this.spielfeld = new char[breite][laenge];
        this.spielerTyp = spielerTyp;

        this.spielfeld = besetzeLeerzeichen(spielfeld);
        this.spielfeld = besetzeSpieler(anzahlAliens, breite, laenge);

    }
    /**
     * Die Funktion besetzt das, als Parameter uebergebene Spielfeld, zufällig mit Aliens und einem Spieler
     * und erzeuge dementsprechend die Objekte. Zudem wird beim erzeugen des Spieler-Objektes noch der
     * Spielertyp abgefragt und dieser spezielle erzeugt.
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @return Ein Char[][] besetzt mit einer bestimmten Anzahl von Aliens und einem Spieler.
     */
    char[][] besetzeSpieler(int anzahlAliens, int breite, int laenge) {
        int xKoordSpieler = (int) (Math.random() * breite);		// Erzeuge zufaellige x-Koordinate
        int yKoordSpieler = (int) (Math.random() * laenge);
        switch (spielerTyp) {                                   // Fragt den Spielertyp ab
            case 0:
                spieler = new Artillerie(xKoordSpieler, yKoordSpieler);     // Erzeugt Artillerie Objekt
                break;
            case 1:
                spieler = new Scharf(xKoordSpieler, yKoordSpieler);         // Erzeugt Scharfschuetzen Objekt
                break;
            default:
                System.out.println("Fehler mit dem Spielertyp");
                break;
        }

        spielfeld[xKoordSpieler][yKoordSpieler] = 'P';                      // Besetzte Koordinaten im Spielfeld

        for (int i = 0; i < anzahlAliens; i++) {
            int x = (int) (Math.random() * breite);		// Erzeuge zufaellige x-Koordinate
            int y = (int) (Math.random() * laenge);     // Erzeuge zufaellige y-Koordinate
			/*
				Die While-Schleife ueperprueft ob die Koordinaten des
				Spielfeldes schon mit einem Alien oder Spieler besetzt
				sind, ansonsten erzeugt sie solange neue Koordinaten,
				bis er einen leeren Platz findet.
			 */
            while (spielfeld[x][y] == 'P' || spielfeld[x][y] == 'A') {
                x = (int) (Math.random() * breite);
                y = (int) (Math.random() * laenge);
            }

            spielfeld[x][y] = 'A';                                          // Besetzte Koordinaten im Spielfeld
            Alien alien = new Alien(x, y);                                  // Erzeuge Alien-Objekt
            aliens[i] = alien;

        }
        return spielfeld;
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
                str.append(spielfeld[i][j]);
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
    public static char[][] besetzeLeerzeichen(char[][] spielfeld) {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                spielfeld[i][j] = ' ';
            }
        }
        return spielfeld;
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
    public char[][] getSpielfeld() {
        return spielfeld;
    }
    /**
     * Zum Aendern der Liste von Alien-Objekten.
     * @param value  Neuer Wert als String des Spielfeld an den Koordinaten X und Y.
     * @param x  Integer als X-Koordinate
     * @param y  Integer als Y-Koordinate.
     */
    public void setSpielfeld(char value, int x, int y) {
        this.spielfeld[x][y] = value;
    }
}