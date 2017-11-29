/**
 * Diese Klasse erzeugt ein Spielfeld vom Type Char[][] mit zufällig besetzten Spieler und Aliens.
 *
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Map {
    /**
     * Die Main-Methode prueft als erstes die Kommandozeilenparameter auf Fehler.
     * Dann wird ein Spielfeld vom Type Char[][] erzeugt und mit Leerzeichen besetzt.
     * Danach wird das Spielfeld zufaellig mit einen Spieler und einer gewissen Anzahl von Aliens besetzt.
     * Zuletzt wird das Spielfeld im Terminal fargestellt.
     * param args 	Kommandozeilenparameter sollte aus 3 Elementen bestehen der Alienanzahl, der Breite und Laenge.
     */
    char[][] spielfeld;
    Player spieler;
    Alien[] aliens;

    public Map(int breite, int leange, int anzahlAliens) {
        this.aliens = new Alien[anzahlAliens];
        this.spielfeld = new char[breite][leange];
        this.spielfeld = besetzeLeerzeichen(spielfeld);
        this.spielfeld = besetzeSpieler(anzahlAliens, breite, leange);

    }
    /**
     * Die Funktion besetzt das, als Parameter uebergebene Spielfeld, zufällig mit Aliens und einem Spieler.
     *
     * @param anzahlAliens		Anzahl von Aliens als Integer.
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Leange des Spielfeld als Integer.
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
     * Mit dieser Funktion wird das Spielfeld im Terminal ausgegeben.
     */
    public void ausgabeSpielfeld() {
        System.out.println("Spielfeld:");
        for (int i = 0; i < spielfeld.length; i++) {       // Jede Zeile des Spielfelds wird betrachtet.
            if (i == 0) {
                System.out.print("  ");
                for (int t = 0; t < spielfeld[0].length; t++) {
                    System.out.print(t);
                }
                System.out.println();
                System.out.println(" " + repeat(spielfeld[0].length + 2, "*"));
            }
            System.out.print(i + "*");
            for (int j = 0; j < spielfeld[i].length; j++) {// Jede Spalte des Spielfelds wird betrachtet.
                System.out.print(spielfeld[i][j]);
            }
            System.out.println("*");
        }
        System.out.println(" " + repeat(spielfeld[0].length + 2, "*"));
    }

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