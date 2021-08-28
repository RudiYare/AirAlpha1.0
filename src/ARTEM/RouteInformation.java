package ARTEM;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class RouteInformation {
    public int starting_airport;
    public int finishing_airport;
    public long starting_time;
    public ArrayList<Timeline> transfers;
    public Map<Integer, Long> time;
    public Map<Integer, Long> prices;
    private ArrayList<String> result;
    public RouteInformation(ArrayList<Timeline> transfers, Map<Integer, Long> time,
                            Map<Integer, Long> prices, int starting_airport,
                            int finishing_airport, long starting_time) {
        this.transfers = transfers;
        this.time = time;
        this.prices = prices;
        this.starting_airport = starting_airport;
        this.finishing_airport = finishing_airport;
        this.starting_time = starting_time;
    }

    private void addNewDateToRoute(Timestamp date) {
        String year; String month; String day; String hours; String minute;
        year = Integer.toString(date.toLocalDateTime().getYear());
        if (date.toLocalDateTime().getMonth().getValue() < 10) {
            month = "0" + Integer.toString(date.toLocalDateTime().getMonth().getValue());
        } else {
            month = Integer.toString(date.toLocalDateTime().getMonth().getValue());
        }
        if (date.toLocalDateTime().getDayOfMonth() < 10) {
            day = "0" + Integer.toString(date.toLocalDateTime().getDayOfMonth());
        } else {
            day = Integer.toString(date.toLocalDateTime().getDayOfMonth());
        }
        if (date.toLocalDateTime().getHour() < 10) {
            hours = "0" + Integer.toString(date.toLocalDateTime().getHour());
        } else {
            hours = Integer.toString(date.toLocalDateTime().getHour());
        }
        if (date.toLocalDateTime().getMinute() < 10) {
            minute = "0" + Integer.toString(date.toLocalDateTime().getMinute());
        } else {
            minute = Integer.toString(date.toLocalDateTime().getMinute());
        }
        result.add(day + "." + month + "." + year + " " + hours + ":" + minute);
    }

    public ArrayList<String> init(Network net) {
        result = new ArrayList<>();
        result.add(net.getAirportInformationAsString(starting_airport));
        result.add(net.getAirportInformationAsString(finishing_airport));
        long flight_time = time.get(finishing_airport);
        result.add(Long.toString(flight_time / 1000 / 3600) + " ч. " + Long.toString((flight_time / 60000) % 60) + " мин.");
        double flight_price = 0;
        for (int transfer = 0; transfer < transfers.size(); transfer++) {
            flight_price += transfers.get(transfer).getPrice();
        }
        result.add(Double.toString(flight_price));
        Timestamp date;
        date = new Timestamp(starting_time);
        addNewDateToRoute(date);
        date = new Timestamp(starting_time + flight_time);
        addNewDateToRoute(date);
        result.add(Integer.toString(transfers.size()));
        for (int transfer = 0; transfer < transfers.size(); transfer++) {
            result.add(net.getAirportInformationAsString(transfers.get(transfer).getStartingAirport()));
            result.add(net.getAirportInformationAsString(transfers.get(transfer).getFinishingAirport()));
            date = new Timestamp(time.get(transfers.get(transfer).getFinishingAirport()) - transfers.get(transfer).getFlightTime());
            addNewDateToRoute(date);
            date = new Timestamp(time.get(transfers.get(transfer).getFinishingAirport()));
            addNewDateToRoute(date);
            result.add(Double.toString(transfers.get(transfer).getPrice()));
        }
        return result;
    }
}