package ARTEM;

public class Timeline {

    public int ID;
    private Airport starting_airport;
    private Airport finishing_airport;
    private int starting_time;
    private int flight_time;
    private int price;

    public Timeline(int ID, Airport starting_airport, Airport finishing_airport, int starting_time, int flight_time, int price) {
        this.ID = ID;
        this.starting_airport = starting_airport;
        this.finishing_airport = finishing_airport;
        this.starting_time = starting_time;
        this.flight_time = flight_time;
        this.price = price;
    }

}
