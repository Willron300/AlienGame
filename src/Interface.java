import java.util.Scanner;

public class Interface {
    private Character character;
    private int[] spielfeldGrosse;

    private int maxLange;

    public Interface(Character charakter, int breite, int lange){
        this.character = charakter;
        this.spielfeldGrosse = new int[] {breite, lange};

    }
    public void start(Map spielfeld) {
        String bewegung = "0";
        while(canMove(bewegung, spielfeld)) {
            bewegung = scanMove();
        }
        move(bewegung, spielfeld);
    }
    public String scanMove(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
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
        character.setKoorX(neuKoorX);
        character.setKoorY(neuKoorY);
        spielfeld.aktualisiereSpielfeld();

    }
    public boolean canMove(String bewegungString, Map spielfeld) {
        char[] bewegungListe = bewegungString.toCharArray();
        if(checkElements(bewegungListe)) {
            if(checkOrientierung(bewegungListe, spielfeld)){
                return true;
            }
        }
        return false;
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
        if (spielfeld.getSpielfeld()[neuKoorX][neuKoorY] != null) {
            System.out.println("Der Platz ist bereits besetzt");
            return false;
        }
        if (neuKoorX < 0 || neuKoorY < 0 || neuKoorX > spielfeldGrosse[0] || neuKoorY > spielfeldGrosse[1]) {
            System.out.println("Der Platz ist ausserhalb des Spielfeldes");
        }
        return true;
    }
    private  Boolean checkElements (char[] bewegungListe) {
        if (bewegungListe.length > maxLange) {
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

    public void setMaxLange(int maxLange) {
        this.maxLange = maxLange;
    }

    public int getMaxLange() {
        return maxLange;
    }
}
