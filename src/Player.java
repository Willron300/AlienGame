public class Player {
    int koor_x;
    int koor_y;
    int leben = 5;

    public Player(int x, int y) {
        this.koor_x = x;
        this.koor_y = y;
    }

    public void angriff(Alien alien, Map spielfeld) {
        if(distance(alien) < (int) (Math.random() * (spielfeld.spielfeld.length + spielfeld.spielfeld[0].length)) + 1) {
            alien.leben = false;
            spielfeld.spielfeld[alien.koor_x][alien.koor_y] = 'X';
            System.out.println("Der Spieler hat das Alien getroffen, an Postition (" + alien.koor_x + "," + alien.koor_y + ")");
        }else{
            System.out.println("Der Spieler hat das Alien verfehlt, an Postition (" + alien.koor_x + "," + alien.koor_y + ")");
        }

    }

    public int distance(Alien alien) {
        int distance;
        distance = Math.abs(koor_x - alien.koor_x) + Math.abs(koor_y - alien.koor_y);

        return distance;
    }
}
