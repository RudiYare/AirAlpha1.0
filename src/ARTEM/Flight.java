package ARTEM;

import java.util.ArrayList;

public class Flight {
    public String from;
    public String where;
    public String time_from;
    public String time_where;
    public String price;
    public String flight_time;
    public ArrayList<Flight> transfers;
    public void print() {
        System.out.println("FROM: " + from);
        System.out.println("WHERE: " + where);
        System.out.println("TIME_FROM: " + time_from);
        System.out.println("TIME_WHERE: " + time_where);
        System.out.println("PRICE: " + price);
        System.out.println("FLIGHT_TIME: " + flight_time);
        if (transfers != null) System.out.println("transfers_size: " + transfers.size());
    }
}
