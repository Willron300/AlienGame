import java.util.Scanner;

public class AlienGame {
    public static void main(String[] args) {
        boolean fehlermeldung = findeFehler(args);                // Ueberprueft die Parameter der Funktion.
        if (fehlermeldung) {                                    // Falls Fehler auftreten, bricht es hier ab.
            int breite = Integer.parseInt(args[0]);
            int laenge = Integer.parseInt(args[1]);
            int anzahlAliens = Integer.parseInt(args[2]);
            Map spielfeld = new Map(breite, laenge, anzahlAliens);        // Erzeuge Spielfeld.
            spielfeld.ausgabeSpielfeld();
            spiele(spielfeld);
        }
    }
    public static void spiele(Map spielfeld) {

        while(ende(spielfeld)) {
            int[] newKoord = scan();

            angriffSpieler(spielfeld, newKoord);

            angriffAliens(spielfeld);

            spielfeld.ausgabeSpielfeld();
            System.out.println(" Der Spieler hat noch " + spielfeld.spieler.leben + "Hitpoints");
        }
        System.out.print(" GG too easy ");

    }
    public static void angriffSpieler(Map spielfeld, int[] koord) {
        for(Alien alien: spielfeld.aliens) {
            if(alien.koor_x == koord[0] && alien.koor_y == koord[1]) {
                spielfeld.spieler.angriff(alien, spielfeld);
            }
        }
    }
    public static void angriffAliens(Map spielfeld) {
        for(Alien alien: spielfeld.aliens) {
            if(alien.leben) {
                alien.angriff(spielfeld.spieler, spielfeld);
            }
        }
    }

    public static int[] scan(){
        int[] newKoord = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wohin soll der Spieler angreifen? Gebe 2 Werte ein (X-Koordinate, Y-Koordinate) : ");
        int anzahl = 1;
        while (anzahl >= 0) {
            int s = scanner.nextInt();
            newKoord[anzahl] = s;
            System.out.println(s);
            anzahl--;
        }
        return newKoord;
    }
    public static Boolean ende(Map spielfeld) {
        if(spielfeld.spieler.leben < 1)
            return false;

        Boolean check_alien = false;
        for(Alien alien: spielfeld.aliens){
            if (alien.leben){
                check_alien = true;
            }
        }
        return check_alien;
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
