package ARTEM;

public class Timeline {

    private int starting_airport;
    private int finishing_airport;
    private int starting_time;
    private int flight_time;
    private int price;

    public Timeline(int starting_airport, int finishing_airport, int starting_time, int flight_time, int price) {
        this.starting_airport = starting_airport;
        this.finishing_airport = finishing_airport;
        this.starting_time = starting_time;
        this.flight_time = flight_time;
        this.price = price;
    }

}
