/**
 * Diese Klasse ist fuer die Bewegung seinen Character verantwortlich.
 * Es implementiert aus dem Moveable-Interface.
 *
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Bewegung implements Moveable {
    private Character character;
    private int[] spielfeldGrosse;
    /**
     * Der Konstruktor verandert die Klassenvariabeln.
     *
     * @param charakter		    Der Character welcher sich bewegen soll
     * @param breite		    Breite des Spielfeld als Integer.
     * @param laenge		    Laenge des Spielfeld als Integer.
     */
    public Bewegung(Character charakter, int breite, int laenge) {
        this.character = charakter;
        this.spielfeldGrosse = new int[] {breite, laenge};

    }
    /**
     * Diese Funktion startet die Bewegung des Character. Zunaechst werden das Spielfeld ueberprueft, ob der
     * Character sich ueberhaupt bewegen kann. Danach wird ein String erzeugt, welche validiert wird. Danach
     * werden die Koordinaten des Characters veraendert. Zuletzt wird dann das Character Array in Map nochmal
     * aktualisiert.
     *
     * @param spielfeld		    Der Character welcher sich bewegen soll
     */
    public void start(Map spielfeld) {
        if (checkSpielfeld(spielfeld)) {
            String bewegung = "0";
            while (canMove(bewegung, spielfeld)) {
                bewegung = character.scanMove();
            }
            move(bewegung, spielfeld);
            spielfeld.aktualisiereSpielfeld();
        } else {
            System.out.printf("Der Charakter an Position %s kann sich nicht bewegen. Alle Positioen in Reichweite"
                    + " sind bereits besetzt.\n", character.toString());
        }
    }

    /**
     * Diese Funktion veraendert die Koordinaten des Characters abhaengig von den Bewegungs String.
     * @param bewegungString		    Der Character welcher sich bewegen soll
     * @param spielfeld		    Der Character welcher sich bewegen soll
     */
    public void move(String bewegungString, Map spielfeld) {
        char[] bewegungListe = bewegungString.toCharArray();
        int neuKoorX = character.getKoorX();
        int neuKoorY = character.getKoorY();
        for (char bewegungChar: bewegungListe) {
            switch (bewegungChar) {
                case 'a':
                    neuKoorX--;
                    break;
                case 'w':
                    neuKoorY--;
                    break;
                case 's':
                    neuKoorY++;
                    break;
                case 'd':
                    neuKoorX++;

                    break;
                default:
                    break;
            }
        }
        if (character instanceof Player) {
            System.out.print("Der Spieler ");
        } else {
            System.out.print("Der Alien ");
        }
        System.out.printf("hat sich bewegt. Alte Koordinaten: %s; neue Koordinaten: (%d,%d)\n",
                character.toString(), neuKoorX, neuKoorY);

        character.setKoorX(neuKoorX);
        character.setKoorY(neuKoorY);
    }
    /**
     * Diese Funktion ueperprueft den String auf Fehler.
     * Als erstes wird der String auf falsche Elemente ueperprueft und dann auf die Orientierung.
     * @param bewegungString		    Der String der Bewegung
     * @param spielfeld		            Das Spielfeld
     * @return boolean                  False wenn alles okay, ansonsten true
     */
    public boolean canMove(String bewegungString, Map spielfeld) {
        char[] bewegungListe = bewegungString.toCharArray();
        if (checkElements(bewegungListe)) {
            if (checkOrientierung(bewegungListe, spielfeld)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Diese Funktion ueperprueft die Bewegung auf Fehler.
     * Zum Beispiel ob die neuen Koordinaten bereits besetzt sind oder ausserhalb des Spielfeldes.
     * @param bewegungListe		        Ein Char Array mit den einzelen Bewegungsschritten
     * @param spielfeld		            Das Spielfeld
     * @return boolean                  True wenn alles okay, ansonsten false
     */
    public  Boolean checkOrientierung(char[] bewegungListe, Map spielfeld) {
        int neuKoorX = character.getKoorX();
        int neuKoorY = character.getKoorY();

        for (char bewegungChar: bewegungListe) {
            switch (bewegungChar) {
                case 'a':
                    neuKoorX--;
                    break;
                case 'w':
                    neuKoorY--;
                    break;
                case 's':
                    neuKoorY++;
                    break;
                case 'd':
                    neuKoorX++;
                    break;
            }
        }

        if (neuKoorX < 0 || neuKoorY < 0 || neuKoorX > spielfeldGrosse[0] - 1 || neuKoorY > spielfeldGrosse[1] - 1) {
            if (character instanceof Player) {
                System.out.println("Der Platz ist ausserhalb des Spielfeldes");
            }
            return false;
        }
        if (spielfeld.getSpielfeld()[neuKoorY][neuKoorX] != null) {
            if (character instanceof Player) {
                System.out.println("Der Platz ist bereits besetzt");
            }
            return false;
        }

        return true;
    }
    /**
     * Diese Funktion ueperprueft ob eine Bewegung ueberhaupt moeglich ist.
     * Falls das Spielfeld so voll ist das keine Bewegung moeglich ist wird ein false zurueck gegeben.
     * @param spielfeld		            Das Spielfeld
     * @return boolean                  True wenn es mindenstens einen freien Platz gibt, ansonsten false
     */
    public Boolean checkSpielfeld(Map spielfeld) {
        int koorX = character.getKoorX();
        int koorY = character.getKoorY();
        for (int i = -character.getBewegungsMax(); i <= character.getBewegungsMax(); i++) {
            int b = Math.abs(i) - 2;
            for (int a = Math.abs(i) - 2; a <= Math.abs(b); a++)  {
                try {
                    if (spielfeld.getSpielfeld()[koorY + i][koorX + a] == null) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return false;
    }
    /**
     * Diese Funktion ueperprueft die einzelen Elemente des Char Arrays auf die richtigen Elemente.
     * Es sind bestimmte Elemente erlaubt.
     * @param bewegungListe		        Das Char Array
     * @return boolean                  True wenn alles okay, ansonsten false
     */
    private  Boolean checkElements(char[] bewegungListe) {
        if (bewegungListe.length > character.getBewegungsMax()) {
            return false;
        }
        for (char bewegungChar: bewegungListe) {
            switch (bewegungChar) {
                case 'a':
                    break;
                case 'w':
                    break;
                case 's':
                    break;
                case 'd':
                    break;
                default:
                    return false;
            }
            return true;
        }
        return true;
    }
}
