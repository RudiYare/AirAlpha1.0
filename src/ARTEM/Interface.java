package ARTEM;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Interface {

    private Network net;


    public Interface() {
        net = new Network();
    }

    public Airport getAirportByID(int ID) {
        return net.getAirport(ID);
    }

    public ArrayList<String> getNearestAirports(String title) {
        ArrayList<String> titles = net.getTitles();
        ArrayList<String> answer = new ArrayList<>();

        Algorithm s = new Algorithm(title);
        int max_match = 0;
        ArrayList<Object[]> res = new ArrayList<>();
        for (String text : titles) {
            text = s.normalizeString(text);
            if ((Integer)(s.maxSubstring(text)[0]) >= 2) {
                res.add(new Object[] {(Integer)(s.maxSubstring(text)[0]), text});
            }
        }
        //Collections.sort();
        System.out.println(max_match);
        if (max_match >= 2) {
            for (String text : titles) {
                text = s.normalizeString(text);
                if ((Integer)s.maxSubstring(text)[0] == max_match && answer.size() < 5) {
                    answer.add(text);
                }
            }
        }
        return answer;
    }

    public int createAirport(int x, int y, String title) {

        int ID;
        do {
            ID = (int) (Math.random() * 100000000);
        } while (net.contains(ID));

        Airport single_airport = new Airport(ID, title, x, y);
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
