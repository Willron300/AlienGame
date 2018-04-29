/**
 * Eine doppelt verkette zirkulaere List von Objekten.
 * 4 Klassenvariabeln: Den Vorgaenger(predessor), den Nachfolger(succesor), das zu speichernde Objekt(data)
 * und den index.
 * @author Willi Schueler 4302326 Gruppe 3B
 * @author Tim Hunte 4919764 Gruppe 3B
 */
public class MyList implements ListItems {
    private MyList successor = null;
	private MyList predessor = null;
	private Object data = null;
	private Integer index = null;

    /**
     * Konstruktor
     */
    public MyList() {
        this.successor = this;
        this.predessor = this;
        this.data = null;
    };

    /**
     * Konstruktor falls alle Daten bekannt.
     * @param successor     Nachfolger
     * @param predessor     Vorgaenger
     * @param data          Objekt
     */
    public MyList(MyList successor, MyList predessor, Object data) {
        this.successor = successor;
        this.predessor = predessor;
        this.data = data;
    };
    /**
     * Konstruktor falls Nachfolger und Vorgaenger bekannt.
     * @param successor     Nachfolger
     * @param predessor     Vorgaenger
     */
    public MyList(MyList successor, MyList predessor) {
        this.successor = successor;
        this.predessor = predessor;
        this.data = null;
    };
    /*
    Methode zur Ausgabe der Liste
     */
    public void printList(MyList list) {
        int b = list.length();
        MyList a = list;
        System.out.println("Anfang Print");
        for (int i = 0; i<=b; i++) {
            System.out.println("data: " + a.data + ", index: " + a.index+  ", pre: " + a.predessor.data+ ", se: " + a.successor.data);
            a = a.successor;
        }
        System.out.println("Ende Print");
    }

    /**
     * Sortierfunktion fuer die List.
     */
    public void sort() {
        MyList l = new MyList(successor, predessor);
        successor.predessor = l;
        predessor.successor = l;

        this.predessor = this;
        this.successor = this;
        int a = l.length();
        for (int i = 0; i < a; i++) {
            Item k = (Item)l.getItem(0);

            for (int j = 1; j <= l.length()-1; j++) {

                //if (k < (Integer)l.getItem(j)) {
                if (0 < k.compareTo(l.getItem(j))) {
                    k = (Item)l.getItem(j);
                }
            }
            l.delete(k);
            insert(k);
        }
    }

    /**
     * Ueberschreibt die toString- Methode.
     * @return String die Liste.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        MyList a = this;
        int b = length();
        for (int i = 0; i<b; i++) {
            a = a.successor;
            s.append(a.data);
            s.append(" -- >");
        }
        return s.toString();
    }

    /**
     * Abfrage ob Liste leer ist.
     * @return      Boolean ob Liste leer
     */
    @Override
    public boolean isEmpty() {
        return successor.data == null;
    }

    /**
     * Bestimmt rekrusiv die Laenge der Liste
     * @return Integer Laenge der Liste.
     */
    @Override
    public int length() {
        if (isEmpty())
            return 0;
        else
            return 1 + successor.length();

    }

    /**
     * Gibt das erste Element der Liste zurueck.
     * @return      Objekt
     */
    @Override
    public Object first() {
        if (data == null)
            return null;
        else
            return successor.data;
    }

    /**
     * Fuegt das Objekt an erste Stelle in der Liste.
     * @param e  das einzufuegende Objekt
     */
    @Override
    public void insert(Object e) {
        if (data == null) {
            MyList l = new MyList(successor, successor.predessor);
            successor.predessor = l;
            l.data = e;
            successor = l;
            successor.updateIndex(-1);
        } else {
            successor.insert(e);
        }
    }

    /**
     * Fuegt das Objekt an die letzte Stelle in der Liste.
     * @param e  das einzufuegende Objekt
     */
    @Override
    public void append(Object e) {

        if (data == null) {
            MyList l = new MyList(this, predessor, e);

            predessor.successor = l;
            predessor = l;

            predessor.updateIndex(length()-2);
        } else {
            successor.append(e);
        }
    }

    /**
     * Fuegt das Element sortiert in die Liste ein.
     * @param e  das einzufuegende Objekt
     */
    @Override
    public void sortedInsert(Object e) {
        insert(e);
        sort();
    }

    /**
     * Sucht ein Objekt in der List.
     * @param e     das zu suchende Objekt
     * @return      MyList      Gibt das Listenelement in welcher das Objekt gespeichert ist zurueck
     */
    public MyList find(Object e) {
        if (isEmpty()) {
            if (e.equals(data)) {
                return this;
            }
            return null;
        } else {
            if (e.equals(data)) {
                return this;
            } else {
                return successor.find(e);
            }
        }
    }

    /**
     * Loescht ein Objekt aus der Liste
     * @param e das zu loeschende Objekt
     */
    @Override
    public void delete(Object e) {
        MyList l = find(e);
        if (l != null) {

            MyList s = l.successor;
            MyList p = l.predessor;

            l.predessor.successor = s;
            l.successor.predessor = p;

            if (l.predessor.index == null) {
                l.updateIndex(-2);
            } else {
                l.updateIndex(l.predessor.index  - 1);
            }
        }
    }

    /**
     * ???
     */
    @Override
    public void iterator() {
    }

    /**
     * Gibt das Item an der Stelle i zurueck
     * @param i   Index
     * @return      Object and der Stelle i
     */
    @Override
    public Object getItem(int i) {
        if (successor.index == i) {
            return successor.data;
        } else {
            return successor.getItem(i);
        }
    }

    /**
     * Aktualisiert rekrusiv den Index aller nachfolgenden Listenelmente.
     * @param i     Index Integer des letzten Listenelementes.
     */
    public void updateIndex(int i) {
        if(data != null) {
            index = i + 1;
            successor.updateIndex(index);
        }
    }
}
