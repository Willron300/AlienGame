public class MyList implements ListItems {
    private MyList successor = null;
	private MyList predessor = null;
	private Object data = null;

	public static void main(String args[]) {
        MyList list = new MyList();
        list.insert(new String("Hallo"));
        System.out.println(list);
        list.insert(new String("Kacke"));
        System.out.println(list);
        list.insert(new String("die"));
        System.out.println(list);
        list.insert(new String("Wand"));
        System.out.println(list);
        list.insert(new String("an"));
        System.out.println(list);
        list.append(new String("!"));
        System.out.println(list);
        System.out.println(list.length());
    }

    public MyList() {
        this.successor = this;
        this.predessor = this;
        this.data = null;
    };
    public MyList(MyList successor, MyList predessor, Object data) {
        this.successor = successor;
        this.predessor = predessor;
        this.data = data;
    };
    public MyList(MyList successor, MyList predessor) {
        this.successor = successor;
        this.predessor = predessor;
        this.data = null;
    };


    public String toString() {
        return (successor.data == null ? " |--" : " --> " + successor.data + successor);
    }

    public MyList getNext() {
        return successor;
    }

    @Override
    public boolean isEmpty() {
        return successor == null;
    }

    @Override
    public int length() {
        if (successor.data == null)
            return 0;
        else
            return 1 + successor.length();

    }

    @Override
    public Object first(Object e) {
        if (data == null)
            return null;
        else
            return successor.data;
    }

    @Override
    public void insert(Object e) {
        if (data == null) {
            System.out.println("1");
            MyList l = new MyList(successor, successor.predessor);
            successor.predessor = l;
            l.data = e;
            successor = l;
        } else {
            System.out.println("3");
            successor.insert(e);
        }
    }

    @Override
    public void append(Object e) {
        if (data == null) {
            MyList l = new MyList(predessor.successor, predessor);
            l.data = e;
            predessor.successor = l;
            predessor = l;
        } else {
            successor.append(e);
        }
    }

    @Override
    public void sortedInsert(Object e) {

    }

    @Override
    public void delete(Object e) {

    }

    @Override
    public void iterator() {
    }

    @Override
    public Object getItem() {
        return null;
    }
}
