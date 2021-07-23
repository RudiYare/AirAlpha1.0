package ARTEM;

public class Interface {

    private Network net;

    public Interface() {
        net = new Network();
    }

    public void create_airport(int x, int y) {

        int ID;
        do {
            ID = (int) (Math.random() * 100000000);
        } while (net.contains(ID));

        Airport single_airport = new Airport(ID, x, y);
        net.addNewAirport(single_airport);

    }

}
