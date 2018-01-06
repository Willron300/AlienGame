import java.util.Scanner;

public class Interface {
    private Character character;
    private int[] spielfeldGrosse;

    public Interface(Character charakter, int breite, int lange){
        this.character = charakter;
        this.spielfeldGrosse = new int[] {breite, lange};

    }
    public void start(Map spielfeld) {
        if (checkSpielfeld(spielfeld)) {
            String bewegung = "0";
            while(canMove(bewegung, spielfeld)) {
                bewegung = character.scanMove();
            }
            move(bewegung, spielfeld);
            spielfeld.aktualisiereSpielfeld();
        } else {
            System.out.printf("Der Charakter an Position %s kann sich nicht bewegen. Alle Positioen in Reichweite" +
                    " sind bereits besetzt.\n", character.toString());
        }
    }

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
        System.out.printf("hat sich bewegt. Alte Koordinaten: %s; neue Koordinaten: (%d,%d)\n", character.toString(),neuKoorX, neuKoorY);

        character.setKoorX(neuKoorX);
        character.setKoorY(neuKoorY);
    }
    public boolean canMove(String bewegungString, Map spielfeld) {
        char[] bewegungListe = bewegungString.toCharArray();
        if(checkElements(bewegungListe)) {
            if(checkOrientierung(bewegungListe, spielfeld)){
                return false;
            }
        }
        return true;
    }
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

        if (neuKoorX < 0 || neuKoorY < 0 || neuKoorX > spielfeldGrosse[0]-1 || neuKoorY > spielfeldGrosse[1]-1) {
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
    public Boolean checkSpielfeld(Map spielfeld) {
        int koorX = character.getKoorX();
        int koorY = character.getKoorY();
        for (int i=-character.getBewegungsMax(); i<=character.getBewegungsMax() ; i++) {
            int b=Math.abs(i)-2;
            for (int a= Math.abs(i)-2; a<=Math.abs(b) ; a++)  {
                try {
                    if (spielfeld.getSpielfeld()[koorY+i][koorX+a] == null) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ;
                }
            }
        }
        return false;
    }
    private  Boolean checkElements (char[] bewegungListe) {
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
