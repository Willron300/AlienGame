import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Diese Klasse erzeugt ein Wall Objekt. Die Klasse hat 3 Variabeln, der Typ(type) steht für Wall(1) oder fuer eine
 * Tuer(0). Zudem ein Marker, welcher beim Erzeugen des Irrgarten noetig ist. Zudem die Liste von allen Nachbarn
 * Wall Objekte.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class Wall extends AlienGameObject {
    private int type;
    private boolean mark = false;
    private List<Wall> list = new ArrayList<Wall>();
    /**
     * Beim erzeugen des Laserstrahl-Objektes werden die Klassenvariabeln neu definiert.
     * Zudem wird ein zufaelliger Typ erzeugt.
     * @param x		x-Koordinate
     * @param y		y-Koordinate
     */
    public Wall(int x, int y) {
        super(x, y);
        if ((int) (Math.random() * 100) > 10) {
            this.type = 1;
        } else {
            this.type = 0;
        }
    }
    /**
     * Die Funktion gibt einen zufaellig gewaehlten Nachbar von diesem Objekt zurueck.
     * @return  Wall-Objekt   ein zufaelliger Nachbar
     */
    public Wall getnneighourWall() {
        int rnd = new Random().nextInt(list.size());
        Wall nextWall = list.get(rnd);
        list.remove(rnd);
        return nextWall;
    }
    /**
     * Die Funktion ueberschreibt die toString Methode und erzeugt ein String mit dem Type des Wall-Objektes.
     * return str       String Abhaengig vom Typ ein # oder ein O
     */
    @Override
    public String toString() {
        if (type == 1) {
            return "#";
        } else {
            return "O";
        }
    }
    /**
     * Abfrage des Types.
     * @return  int type    Der Type des Wall Objektes
     */
    public int getType() {
        return type;
    }
    /**
     * Abfrage des Mark.
     * @return  boolean   Ob das Wall-Objekt bereits abgearbeitet.
     */
    public boolean getMark() {
        return mark;
    }
    /**
     * Fuegt ein Wall-Objekt in die Liste ein. Setzt bei manchen Wall-Objekten den Marker auf True.
     * @param wall   Wall Objekt
     */
    public void setList(Wall wall) {
        this.list.add(wall);
        int random = (int) (Math.random() * 100);
        if (random < 20) {
            wall.setMark(true);
        }
    }
    /**
     * Abfrage des Liste von Nachbar-Wall-Objekten.
     * @return  Wall[]     Liste von Wall-Objekten
     */
    public List<Wall> getList() {
        return list;
    }
    /**
     * Zum Aendern der Mark-Variable.
     * @param mark   Wird auf true gesetzt, wenn das Wall-Objekt bereits abgearbeitet wurde.
     */
    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
