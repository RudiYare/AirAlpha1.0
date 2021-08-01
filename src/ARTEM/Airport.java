package ARTEM;

import java.util.ArrayList;

public class Airport {

    public int ID;

    // координаты аэропорта с карты
    private int x;
    private int y;
    private String title;

    // список аэропоротов, в которые есть перелёты из текущего аэропорта
    private ArrayList<Integer> related;

    // создание нового аэропорта
    public Airport(int ID, String title, int x, int y) {
        this.x = x; this.y = y; this.ID = ID; this.title = title;
        this.related = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    // из текущего аэропорта теперь можно летать в аэропрот #ID
    public Boolean newFlight(int ID) {
        if (this.related.contains(ID)) {
            return Boolean.FALSE;
        }
        this.related.add(ID);
        return Boolean.TRUE;
    }

    public ArrayList<Integer> getRelated() {
        return related;
    }
}
