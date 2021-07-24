package ARTEM;

public class Interface {

    private Network net;


    public Interface() {
        net = new Network();
    }

    public int createAirport(int x, int y) {

        int ID;
        do {
            ID = (int) (Math.random() * 100000000);
        } while (net.contains(ID));

        Airport single_airport = new Airport(ID, x, y);
        net.addNewAirport(single_airport);
        return ID;

    }

    public int createFlight(int sID, int eID, int starting_time, int flight_time, int price) {
        int ID;
        do {
            ID = (int) (Math.random() * 100000000);
        } while (net.contains(ID));

        Airport starting_airport = net.getAirport(sID);
        Airport finishing_airport = net.getAirport(sID);

        Timeline single_flight = new Timeline(ID, starting_airport, finishing_airport, starting_time, flight_time, price);
        net.addNewFlight(single_flight);

        return ID;
    }

}
