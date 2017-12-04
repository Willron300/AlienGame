/**
 * Diese Klasse erzeugt ein Spielfeld vom Typ char[][] und ein Player-Objekt und eine Liste von Alien-Objekten.
 * Das Spielfeld wird mit Leerzeichen sowie P und A bestetzt und das entsprechende Objekt mit den passenden
 * Koordinaten erzeugt.
 * Die Klasse hat drei Klassenavariabeln: spielfeld, spieler und aliens.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Map {
    char[][] spielfeld;
    Player spieler;
    Alien[] aliens;
    /**
     * Dann wird ein Spielfeld vom Type Char[][] erzeugt und mit Leerzeichen besetzt.
     * Danach wird das Spielfeld zufaellig mit einen Spieler und einer gewissen Anzahl von Aliens besetzt.
     * Wobei auch das Player-Objekt und die Liste von Alien-Objekte erzeugt werden.
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     */
    public Map(int breite, int laenge, int anzahlAliens) {
        this.aliens = new Alien[anzahlAliens];
        this.spielfeld = new char[breite][laenge];
        this.spielfeld = besetzeLeerzeichen(spielfeld);
        this.spielfeld = besetzeSpieler(anzahlAliens, breite, laenge);

    }
    /**
     * Die Funktion besetzt das, als Parameter uebergebene Spielfeld, zufällig mit Aliens und einem Spieler
     * und erzeuge dementsprechend die Objekte
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     * @return Ein Char[][] besetzt mit einer bestimmten Anzahl von Aliens und einem Spieler.
     */
    char[][] besetzeSpieler(int anzahlAliens, int breite, int laenge) {
        for (int i = 0; i < anzahlAliens + 1; i++) {
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
			/*
				Das Spielfeld soll nur einen Spieler haben, daher wird beim dem ersten Ausfuhren der For-Schleife
				ein Spieler erzeugt und ansonstens Aliens.
			*/
            if (i == 0) {
                spielfeld[x][y] = 'P';
                spieler = new Player(x, y);
            } else {
                spielfeld[x][y] = 'A';
                Alien alien = new Alien(x, y);
                aliens[i - 1] = alien;
            }
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
}