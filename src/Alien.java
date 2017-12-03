public class Alien {
    int koor_x;
    int koor_y;
    Boolean leben = true;

    public Alien(int x, int y) {
        this.koor_x = x;
        this.koor_y = y;
    }

    public void angriff(Player spieler, Map spielfeld) {
        if (spieler.leben > 0) {
            if(distance(spieler) < (int) (Math.random() * (spielfeld.spielfeld.length + spielfeld.spielfeld[0].length)) + 1) {
                spieler.leben--;
                System.out.println("Der Alien (" + koor_x + "," + koor_y + ") hat den Spieler getroffen");
            }else{
                System.out.println("Der Alien (" + koor_x + "," + koor_y + ") hat das Spieler verfehlt");
            }
        }
    }
    public int distance(Player spieler) {
        int distance;
        distance = Math.abs(koor_x - spieler.koor_x) + Math.abs(koor_y - spieler.koor_y);

        return distance;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
