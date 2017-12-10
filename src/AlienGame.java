import java.util.*;
/**
 * Die Klasse spielt das Spiel. Zuerst fuehrem wir eine Funktion aus in welcher ein Spielertyp bestimmt wird.
 * Danach wird ein Map Objekt erzeugt in dem alle wichtigen Parameter erzeugt werden.
 * Dann werden neue Koordinaten eingelesen und ueberprueft. Als naechstes hat der Spieler seinen Zug und dann der
 * Alien. Zudem prueft die Klasse nach jedem Zug ob es einen Gewinner gibt. Falls ja wird eine Nachricht generiert.
 *
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class AlienGame {
    /**
     * Die Main-Methode prueft als erstes die Kommandozeilenparameter auf Fehler.
     * Danach kann der Spieler ein Spielertyp bestimmen. Dann wird ein Map Objekt erzeugt.
     * Danach wird die Funktion spiele ausgefuehrt in welcher solange die Zuege wiederholt werden, bis es einen
     * Gewinner gibt.
     * @param args 	Kommandozeilenparameter sollte aus 3 Elementen bestehen der Alienanzahl, der Breite und Laenge.
     */
    public static void main(String[] args) {
        boolean fehlermeldung = findeFehler(args);                      // Ueberprueft die Parameter der Funktion.
        if (fehlermeldung) {                                            // Falls Fehler auftreten, bricht es hier ab.
            int breite = Integer.parseInt(args[0]);
            int laenge = Integer.parseInt(args[1]);
            int anzahlAliens = Integer.parseInt(args[2]);

            int spielerTyp = getSpielerTyp();                           // Waehle ein Spielertyp

            Map spielfeld = new Map(laenge, breite, anzahlAliens, spielerTyp);      // Erzeuge Map-Objekt

            spiele(spielfeld);                                          // Starte das Spiel
        }
    }
    /**
     * Die Funktion zeigt als erstes das Spielfeld an. Nun werden Koordinaten eingelesen und dann hat der Spieler
     * einen Angriffszug und dann die Aliens. Das wird solange wiederholt bis die ende-Funktion, welche ueberprueft
     * ob das Spiel zu Ende ist, die While-Schleife beendet.
     *
     * @param spielfeld		Anzahl von Aliens als Integer.
     */
    public static void spiele(Map spielfeld) {

        while (ende(spielfeld)) {                                         // Ueberpruft ob das Spiel zu Ende ist
            System.out.println(spielfeld);
            System.out.println(" Der Spieler hat noch " + spielfeld.getSpieler().getLeben() + "Hitpoints");

            int[] newKoord = scan(spielfeld);                           // Neue Koordinaten werden eingelesen

            angriffSpieler(spielfeld, newKoord);                        // Angriffszug des Spielers

            angriffAliens(spielfeld);                                   // Angriffszug des Aliens
        }
        gewinnNachricht(spielfeld);                                     // Erzeugt Nachricht falls Spiel zu Ende

    }
    /**
     * Die Funktion sucht alle Aliens, welche attackiert werden sollen, und fuehrt dann einen Angriff auf diese Aliens
     * aus.
     * @param spielfeld		Map-Objekt
     * @param koord		    Liste von Integer mit den anzugreifenden Koordinaten fur den Spieler
     */
    public static void angriffSpieler(Map spielfeld, int[] koord) {
        for (int[] ziele: spielfeld.getSpieler().getZiel()) {                     // Ueperprueft alle Angriffsziele
            for (Alien alien : spielfeld.getAliens()) {
                if (alien.getKoorX() == koord[0] + ziele[0] && alien.getKoorY() == koord[1] + ziele[1]) {
                    spielfeld.getSpieler().angriff(alien, spielfeld);            // Angriffsfunktion der Player-Klasse
                }
            }
        }
    }
    /**
     * Die Funktion laesst jeden Alien den Spieler angreifen.
     * @param spielfeld		Map-Objekt
     */
    public static void angriffAliens(Map spielfeld) {
        for (Alien alien: spielfeld.getAliens()) {
            if (alien.getLeben()) {
                alien.angriff(spielfeld.getSpieler());            // Angriffsfunktion der Alien-Klasse
            }
        }
    }
    /**
     * Diese Funktion erlaubt den User zwei Koordinaten einzulesen. Sie arbeitet dabei mit dem Scanner-Modul.
     * Als erstes werden zwei Koordinaten eingelesen. Danach untersucht die ueberpruefeKoord-Funktion die neuen
     * Koordinaten auf Fehler. Falls der User falsche Koordinaten eingegeben hat, werden alle Moeglichkeiten
     * auf dem Terminal dargestellt.
     *
     * @param spielfeld		Map-Objekt
     * @return Ein int[] besetzt mit neuen Koordinaten
     */
    public static int[] scan(Map spielfeld) {
        int[] newKoord = new int[2];                                    // Koordinaten sollen in Liste stehen

        while (true) {                                                   // Solange wiederholt bis passende gefunden
            try {
                Scanner scanner = new Scanner(System.in);               // Scanner-Modul
                System.out.print("Wohin soll der Spieler angreifen? Gebe 2 Werte ein (X-Koordinate, Y-Koordinate) : ");
                int anzahl = 1;
                while (anzahl >= 0) {                                   // 2 Koordinaten werden eingelesen
                    int s = scanner.nextInt();
                    newKoord[anzahl] = s;                               // in Liste eingefugt
                    anzahl--;
                }
                if (ueberprufeKoord(newKoord, spielfeld)) {             // Ueberprueft Koordinaten auf Fehler.
                    break;                                              // Falls alles i.O. wird While abgebrochen
                } else {

                    /*
				        Hier werden alle moeglichen Koordinaten in dieser For-Schleife auf dem Terminal
				        dargestellt.
			        */
                    System.out.println("Moeglich Koordinaten sind : ");
                    int i = 0;
                    for (Alien alien: spielfeld.getAliens()) {
                        if (alien.getLeben()) {
                            if (i % 10 == 0 && i > 0) {                 // Modulo , damit nach 10 Koord Zeilenumbruch
                                System.out.println();
                            }
                            System.out.print(alien);
                            System.out.print(", ");
                            i++;
                        }
                    }
                    System.out.println();
                }
            } catch (InputMismatchException e) {                           // falls ein String im Terminal angegeben
                System.out.println("Fehler beim Einlesen der Koordinaten. Bitte zwei Integer Werte eingeben.");
            }

        }
        return newKoord;
    }
    /**
     * Diese Funktion ueberprueft die neuen Koordinaten auf falsche Eingaben in Verbindung mit dem Map-Objekt.
     *
     * @param koord		    Liste von Integer mit den neuen Koordinaten
     * @param spielfeld		Map-Objekt
     * @return  true falls keine Fehler gefunden wurden, ansonsten false.
     */
    public static Boolean ueberprufeKoord(int[] koord, Map spielfeld) {
        if (koord[0] == spielfeld.getSpieler().getKoorX() && koord[1] == spielfeld.getSpieler().getKoorY()) {
            System.out.println("Koordinate kann nicht angegriffen werden, dort sitzt der Spieler " + spielfeld.getSpieler());
            return false;
        }
        if (koord[0] < 0 || koord[1] < 0) {
            System.out.println("Eine der Koordinaten ist negativ (" + koord[1] + "," + koord[0] + ")");
            return false;
        }
        if (koord[0] > spielfeld.getSpielfeld().length - 1 || koord[1] > spielfeld.getSpielfeld()[0].length - 1) {
            System.out.println("Koordinaten ausserhalb des Spielfeldes (" + koord[1] + "," + koord[0] + ")");
            return false;
        }
        Boolean checkAlien = false;
        for (Alien alien: spielfeld.getAliens()) {
            if (!alien.getLeben() && koord[0] == alien.getKoorX() && koord[1] == alien.getKoorY()) {
                System.out.println("Koordinaten treffen einen toten Alien (" + koord[1] + "," + koord[0] + ")");
                return false;
            }
            if (koord[0] == alien.getKoorX() && koord[1] == alien.getKoorY()) {
                checkAlien = true;
            }
        }
        if (!checkAlien) {
            System.out.println("Koordinaten treffen keinen Alien (" + koord[1] + "," + koord[0] + ")");
            return false;
        } else {
            return true;
        }
    }
    /**
     * Diese Funktion ueberprueft ob entweder alle Aliens tot sind oder der Spieler tot ist. Falls beide noch leben
     *
     * @param spielfeld		Anzahl von Aliens als Integer.
     * @return true falls Spieler und Aliens noch leben, ansonsten falls.
     */
    public static Boolean ende(Map spielfeld) {
        if (spielfeld.getSpieler().getLeben() < 1) {
            return false;
        }

        Boolean checkAlien = false;
        for (Alien alien: spielfeld.getAliens()) {
            if (alien.getLeben()) {
                checkAlien = true;
            }
        }
        return checkAlien;
    }
    /**
     * Diese Funktion ueberprueft wer das Spiel gewonnen hat und gibt eine entsprechende Nachricht aus.
     *
     * @param spielfeld		Map-Objekt
     */
    public static void gewinnNachricht(Map spielfeld) {
        if (spielfeld.getSpieler().getLeben() == 0) {
            System.out.println("Du Lappen hast vorloren.");
        } else {
            System.out.println("GG too easy. Alle Aliens wurden erlegigt");
        }
    }
    /**
     * Diese Funktion erlaubt den zwischen den Spielertypen zu waehlen. Sie arbeitet dabei mit dem Scanner-Modul.
     * Der eingelesene Integerwert wird noch ueperprueft und dann zurueckgegeben.
     * @return Ein Integer welcher die Typnummer darstellt
     */
    public static int getSpielerTyp() {
        String[] spielerTyp = {"Arteillerie", "Scharschütze"};
        System.out.println("Entscheide welchen Spielertyp du benutzen m\u00f6chtest !");
        System.out.println("Typnummer \t Typ");

        for (int i = 0; i < spielerTyp.length; i++) {
            System.out.println(i + "\t\t" + spielerTyp[i]);
        }
        Scanner scanner = new Scanner(System.in);               // Scanner-Modul
        while (true) {
            System.out.print("Typnummer: ");
            try {
                int s = scanner.nextInt();
                if (0 <= s && s <= spielerTyp.length - 1) {
                    return s;
                } else {
                    System.out.println("Bitte einen korrekte Typnummer eingeben!");
                }

            } catch (InputMismatchException e) {                           // falls ein String im Terminal angegeben
                scanner = new Scanner(System.in);
                System.out.println("Fehler bei der Eingabe. Bitte gebe einen korrekten Integer Wert ein");

            }

        }

    }

    /**
     * Ueperprueft die Parameter der Klasse auf Fehler.
     * Falls ein Fehler gefunden wird, wird eine Fehlermeldung erzeugt und die lokale Variable,
     * der Rueckgabewert, auf "false" gesetzt.
     *
     * @param argumente Die Liste der Argumente die der Klasse AlienGame als Parameter übergeben werden.
     * @return Falls ein Fehler aufgetreten ist "false" ansonsten "true".
     */
    public static boolean findeFehler(String[] argumente) {
        boolean keinProblem = true;
        if (argumente.length != 3) {            // Ueperpruefe ob 3 Argumente in der Liste sind.
            System.out.println("Bitte 3 Werte eingeben!");
            keinProblem = false;
        } else {
            try {                               // Versuche die Parameter in der Liste in Integer umzuwandeln.
                int breite = Integer.parseInt(argumente[0]);
                int laenge = Integer.parseInt(argumente[1]);
                int anzahlAliens = Integer.parseInt(argumente[2]);
                if (anzahlAliens >= breite * laenge) {    // Ueperpruefe ob die Alienanzahl das Spielfeld ueberschreitet.
                    System.out.println("Die Anzahl der Aliens ist groesser als das Spielfeld!");
                    keinProblem = false;
                }
            } catch (NumberFormatException e) { // Falls ein Fehler beim Umwandeln eintritt.
                System.out.println("Bitte 3 korrekte Integer Werte eingeben!");
                keinProblem = false;
            }
        }
        return keinProblem;
    }
}
