package ARTEM;

import java.lang.reflect.Array;
import java.util.*;

public class Network {

    private Map<Integer, Airport> airports;
    private Map<Integer, Timeline> timelines;

    private Map<Integer, Boolean> used;
    private ArrayList<Integer> airports_IDs;
    private ArrayList<Integer> timelines_IDs;

    public Network() {
        airports = new HashMap<Integer, Airport>();
        timelines = new HashMap<Integer, Timeline>();
        used = new HashMap<Integer, Boolean>();
        airports_IDs = new ArrayList<>();
    }

    public Boolean addNewAirport(Airport single_airport) {
        if (airports.containsValue(single_airport)) {
            return Boolean.FALSE;
        }
        final int ID = single_airport.ID;
        airports.put(ID, single_airport);
        airports_IDs.add(ID);
        used.put(ID, Boolean.FALSE);
        return Boolean.TRUE;

    }

    public Boolean contains(int ID) {
        if (airports.containsKey(ID)) return Boolean.TRUE;
        if (timelines.containsKey(ID)) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void dfs(int ID) {
        used.put(ID, Boolean.TRUE);
        Airport current_airport = airports.get(ID);
        ArrayList<Integer> adjacent_airports = current_airport.getRelated();
        for (int adjacent_ID : adjacent_airports) {
            dfs(adjacent_ID);
        }
    }

    public Boolean is_reachable(Airport x, Airport y) {
        for (int u : airports_IDs) {
            used.put(u, Boolean.FALSE);
        }
        dfs(x.ID);
        if (used.get(y.ID)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Airport getAirport(int ID) {
        return airports.get(ID);
    }

    public Boolean addNewFlight(Timeline flight) {
        if (timelines.containsValue(flight)) {
            return Boolean.FALSE;
        }
        final int ID = flight.ID;
        timelines.put(ID, flight);
        timelines_IDs.add(ID);
        return Boolean.TRUE;
    }

    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (int ID : airports_IDs) {
            titles.add(airports.get(ID).getTitle());
        }
        return titles;
    }

}
