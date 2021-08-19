//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ARTEM;

public class Timeline {
    private int starting_airport;
    private int finishing_airport;
    private long starting_time;
    private long flight_time;
    private double price;

    public Timeline(int starting_airport, int finishing_airport, long starting_time, long flight_time, double price) {
        this.starting_airport = starting_airport;
        this.finishing_airport = finishing_airport;
        this.starting_time = starting_time;
        this.flight_time = flight_time;
        this.price = price;
    }

    public void print() {
        System.out.println("Timeline information");
        System.out.printf("Starts from: %s, End in: %s \n Starting time: %s, Flight time: %s. PRICE: %s\n", this.starting_airport, this.finishing_airport, this.starting_time, this.flight_time, this.price);
    }

    public int getFinishingAirport() {
        return this.finishing_airport;
    }
}
