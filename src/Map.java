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

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("Spielfeld: \n   ");
        StringBuilder firstRow = new StringBuilder("   ");
        for (int k = 0; k<spielfeld[0].length; k ++){
            if (k % 10 == 0){
                str.append(k/10);
            }else{
                str.append(" ");
            }
            firstRow.append(k % 10);
        }

        str.append("\n");
        str.append(firstRow.toString());
        str.append("\n ");
        str.append(" " + repeat(spielfeld[0].length + 2, "*"));
        str.append("\n");

        for (int i = 0 ; i<spielfeld.length ; i ++ ){
            if (i % 10 == 0){
                str.append(i/10);
            }else{
                str.append(" ");
            }
            str.append(i % 10 + "*");
            for (int j = 0 ; j < spielfeld[i].length ; j++){
                str.append(spielfeld[i][j]);
            }
            str.append("*\n");
        }
        str.append("  " + repeat(spielfeld[0].length + 2, "*"));
        return str.toString();
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