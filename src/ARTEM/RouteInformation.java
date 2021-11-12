package ARTEM;


import javafx.util.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class RouteInformation {
    public int starting_airport;
    public int finishing_airport;
    public long starting_time;
    private final long SECONDS_IN_WEEK = 604800000;
    public ArrayList<Timeline> transfers;
    public ArrayList<Pair<Long, Long>> transfer_time;
    private ArrayList<String> result;
    public RouteInformation(ArrayList<Timeline> transfers,
                            int starting_airport,
                            int finishing_airport,
                            ArrayList<Pair<Long, Long>> transfer_time,
                            long starting_time) {
        this.transfers = transfers;
        this.starting_airport = starting_airport;
        this.finishing_airport = finishing_airport;
        this.transfer_time = transfer_time;
        this.starting_time = starting_time;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        int MOD = (int)(1e9 + 7);
        hash += (starting_airport) % MOD; hash %= MOD;
        hash += (finishing_airport) % MOD; hash %= MOD;
        hash += (starting_time) % MOD; hash %= MOD;
        hash += (transfers.size()) % MOD; hash %= MOD;
        for (Timeline x : transfers) {
            hash += (x.getStartingAirport()) % MOD; hash %= MOD;
            hash += (x.getFinishingAirport()) % MOD; hash %= MOD;
            hash += (x.getStartingTime()) % MOD; hash %= MOD;
            hash += (x.getFlightTime()) % MOD; hash %= MOD;
        }
        System.out.println(hash);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        RouteInformation other = (RouteInformation) obj;
        if (starting_airport != other.starting_airport)
            return false;
        if (finishing_airport != other.finishing_airport)
            return false;
        if (starting_time != other.starting_time)
            return false;
        if (transfers.size() != other.transfers.size())
            return false;
        return true;
    }

    Flight flight;

    private String addNewDateToRoute(Timestamp date) {
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
        return (year + " " + month + " " + day + " " + hours + ":" + minute);
    }
    public Flight init(Network net) {
        flight = new Flight();
        long flight_time = transfer_time.get(0).getValue() - starting_time;
        double flight_price = 0;
        for (Timeline timeline : transfers) {
            flight_price += timeline.getPrice();
        }
        flight.from = net.getAirportInformationAsString(starting_airport);
        flight.where = net.getAirportInformationAsString(finishing_airport);
        flight.flight_time = Long.toString(flight_time / 1000 / 3600) + " ч. " + Long.toString((flight_time / 60000) % 60) + " мин.";
        flight.price = Double.toString(Math.round(flight_price * 100) / 100.0);
        flight.time_from = addNewDateToRoute(new Timestamp(transfer_time.get(transfer_time.size() - 1).getKey()));
        flight.time_where = addNewDateToRoute(new Timestamp(transfer_time.get(0).getValue()));
        flight.transfers = new ArrayList<>();
        for (int transfer = 0; transfer < transfers.size(); transfer++) {
            Flight t = new Flight();
            t.from = net.getAirportInformationAsString(transfers.get(transfer).getStartingAirport());
            t.where = net.getAirportInformationAsString(transfers.get(transfer).getFinishingAirport());
            t.time_from = addNewDateToRoute(new Timestamp(transfer_time.get(transfer_time.size() - transfer - 1).getKey()));
            t.time_from = addNewDateToRoute(new Timestamp(transfer_time.get(transfer_time.size() - transfer - 1).getValue()));
            t.price = Double.toString(Math.round((transfers.get(transfer).getPrice() * 100) / 100.0));
            flight.transfers.add(flight);

        }
        return flight;

    }
}