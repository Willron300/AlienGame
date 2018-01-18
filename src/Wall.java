import java.util.ArrayList;
import java.util.List;

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
    public boolean checkList() {
        for (int i = 0; i<list.size(); i++) {
            if(!list.get(i).getMark()) {
                return true;
            }
        }
        return false;
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
    }

    public List<Wall> getList() {
        return list;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
