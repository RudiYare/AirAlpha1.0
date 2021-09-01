package ARTEM;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class RouteInformation {
    public int starting_airport;
    public int finishing_airport;
    public long starting_time;
    private final long SECONDS_IN_WEEK = 604800000;
    public ArrayList<Timeline> transfers;
    private ArrayList<String> result;
    public RouteInformation(ArrayList<Timeline> transfers,
                            int starting_airport,
                            int finishing_airport, long starting_time) {
        this.transfers = transfers;
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
        System.out.println("");
        result.add(net.getAirportInformationAsString(starting_airport));
        result.add(net.getAirportInformationAsString(finishing_airport));
        long flight_time = 0;
        long current_time = starting_time;
        for (var i = 0; i < transfers.size(); i++) {
            //transfers.get(i).print();
            long starts_in = current_time % SECONDS_IN_WEEK;
            long flight_start = transfers.get(i).getStartingTime();
            System.out.print(starts_in); System.out.print(" "); System.out.println(flight_start);
            if (flight_start < starts_in) {
                flight_start += SECONDS_IN_WEEK;
            }
            flight_time += flight_start - starts_in + transfers.get(i).getFlightTime();
            current_time += flight_start - starts_in + transfers.get(i).getFlightTime();
        }
        result.add(Long.toString(flight_time / 1000 / 3600) + " ч. " + Long.toString((flight_time / 60000) % 60) + " мин.");
        double flight_price = 0;
        for (int transfer = 0; transfer < transfers.size(); transfer++) {
            flight_price += transfers.get(transfer).getPrice();
        }
        result.add(Double.toString(Math.round(flight_price * 100) / 100.0));
        Timestamp date;
        date = new Timestamp(starting_time);
        addNewDateToRoute(date);
        date = new Timestamp(starting_time + flight_time);
        addNewDateToRoute(date);
        result.add(Integer.toString(transfers.size()));
        for (int transfer = 0; transfer < transfers.size(); transfer++) {
            result.add(net.getAirportInformationAsString(transfers.get(transfer).getStartingAirport()));
            result.add(net.getAirportInformationAsString(transfers.get(transfer).getFinishingAirport()));
            date = new Timestamp(10000);
            addNewDateToRoute(date);
            date = new Timestamp(10000000);
            addNewDateToRoute(date);
            result.add(Double.toString(Math.round((transfers.get(transfer).getPrice() * 100) / 100.0)));
        }
        return result;
    }
}