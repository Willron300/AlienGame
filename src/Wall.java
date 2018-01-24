import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wall extends AlienGameObject {
    private int type;
    private boolean mark = false;
    private List<Wall> list = new ArrayList<Wall>();

    public Wall(int x, int y) {
        super(x,y);
        if ((int) (Math.random() * 100) > 10) {
            this.type = 1;
        }else {
            this.type = 0;
        }
    }
    public Wall getnneighourWall() {
        int rnd = new Random().nextInt(list.size());
        Wall nextWall = list.get(rnd);
        list.remove(rnd);
        return nextWall;
    }

    @Override
    public String toString() {
        if (type == 1) {
            return "#";
        }else {
            return "O";
        }
    }

    public int getType() {
        return type;
    }
    public boolean getMark() {
        return mark;
    }
    public void setList(Wall wall) {
        this.list.add(wall);
        int random = (int)(Math.random() * 100);
        if (random < 20) {
            wall.setMark(true);
        }

    }

    public List<Wall> getList() {
        return list;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
