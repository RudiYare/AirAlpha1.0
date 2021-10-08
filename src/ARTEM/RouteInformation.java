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
        long flight_time = transfer_time.get(0).getValue() - starting_time;
        result.add(Long.toString(flight_time / 1000 / 3600) + " ч. " + Long.toString((flight_time / 60000) % 60) + " мин.");
        double flight_price = 0;
        for (Timeline timeline : transfers) {
            flight_price += timeline.getPrice();
        }
        result.add(Double.toString(Math.round(flight_price * 100) / 100.0));
        Timestamp date;

        date = new Timestamp(transfer_time.get(transfer_time.size() - 1).getKey());
        addNewDateToRoute(date);
        date = new Timestamp(transfer_time.get(0).getValue());
        addNewDateToRoute(date);
        result.add(Integer.toString(transfers.size() - 1));

        for (int transfer = 0; transfer < transfers.size(); transfer++) {
            result.add(net.getAirportInformationAsString(transfers.get(transfer).getStartingAirport()));
            result.add(net.getAirportInformationAsString(transfers.get(transfer).getFinishingAirport()));
            date = new Timestamp(transfer_time.get(transfer_time.size() - transfer - 1).getKey());
            addNewDateToRoute(date);
            date = new Timestamp(transfer_time.get(transfer_time.size() - transfer - 1).getValue());
            addNewDateToRoute(date);
            result.add(Double.toString(Math.round((transfers.get(transfer).getPrice() * 100) / 100.0)));
        }
        return result;
    }
}