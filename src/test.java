import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gebe 2 Werte ein x , y =");
        while (true){
            String s = scanner.next();
            System.out.println(s);

        }

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

}
