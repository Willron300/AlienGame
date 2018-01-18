import java.util.ArrayList;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        Map spielfeld = new Map(10, 10, 5, 1);

        AlienGameObject[][] bla = spielfeld.generateMatchfield(10, 10 );
        kack(bla);

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
}