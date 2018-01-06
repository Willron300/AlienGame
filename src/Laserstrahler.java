import java.util.ArrayList;
import java.util.Arrays;

/**
 * Diese Klasse erzeugt ein Artillerie-Objekt. Sie vererbt aus der Player-Klasse.
 * Das Artillerie Objekt hat eine schlechte Trefferwahrscheinlichkeit aber dafür greift zusatzlich alle Felder
 * darum an.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Laserstrahler extends Player {
    /**
     * Beim erzeugen des Alien-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem werden die Trefferwahrscheinlichkeit und die Zielkoordinaten definiert.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Laserstrahler(int x, int y) {
        super(x, y);
        int trefferWahrscheinlichkeit = 100;
        int[][] ziel = {{0, 0}};
        setTrefferWahrscheinlichkeit(trefferWahrscheinlichkeit);
        setZiel(ziel);
    }
    @Override
    public void angriff(Alien alien, Map spielfeld) {
        if (getZiel().length == 1){
            int aKoorX = alien.getKoorX();
            int aKoorY = alien.getKoorY();

            int pKoorX = getKoorX();
            int pKoorY = getKoorY();

            double difX = aKoorX - pKoorX;
            double difY = aKoorY - pKoorY;
            double dif = Math.abs(Math.max(difX, difY));
            System.out.printf("alien %d, %d ; spieler %d, %d ; dif= %d ; difX = %d ; difY = %d\n", aKoorX, aKoorY, pKoorX, pKoorY, (int) dif, (int) difX, (int) difY);
            int[][] ziel = new int[(int) dif][2];
            for (int i = 1; i<=dif; i++) {
                double kX = (double) 1/i * difX;
                double kY = (double) 1/i * difY;
                int neuKoorX = (int) Math.round(kX);
                int neuKoorY = (int) Math.round(kY);

                ziel[i - 1][0] = neuKoorX;
                ziel[i - 1][1] = neuKoorY;
                System.out.printf("dif= %d ; neu %d, %d \n %s \n", (int) dif, neuKoorX, neuKoorY, Arrays.deepToString(ziel));
            }
            System.out.println(Arrays.deepToString(ziel));
            setZiel(ziel);
        }

        super.angriff(alien, spielfeld);

    }
}
