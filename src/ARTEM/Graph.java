

package ARTEM;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.*;
import java.util.Map.Entry;


public class Graph {
    private Map<Integer, ArrayList<Integer>> connected = new HashMap();
    private Map<Integer, ArrayList<Integer>> connected_reversed = new HashMap();
    private Map<Pair<Integer, Integer>, ArrayList<Timeline>> timelines = new HashMap();
    private Map<Integer, Long> time;
    private Map<Integer, Long> prices;
    private final long SECONDS_IN_WEEK = 604800000;
    private Map<Integer, Boolean> used = new HashMap();

    public Graph(Map<Integer, ArrayList<Timeline>> timelines) {
        time = new HashMap<>();
        prices = new HashMap<>();
        for (Entry<Integer, ArrayList<Timeline>> entry : timelines.entrySet()) {
            Integer starting_airport = entry.getKey();
            ArrayList<Timeline> actual_timelines = entry.getValue();
            time.put(starting_airport, 1000000007L * 1000000007L);
            prices.put(starting_airport, 1000000007L * 1000000007L);
            for (Timeline x : actual_timelines) {
                time.put(x.getFinishingAirport(), 1000000007L * 1000000007L);
                prices.put(x.getFinishingAirport(), 1000000007L * 1000000007L);
                connected.computeIfAbsent(starting_airport, k -> new ArrayList<>());
                connected.get(starting_airport).add(x.getFinishingAirport());
                connected_reversed.computeIfAbsent(x.getFinishingAirport(), k -> new ArrayList<>());
                connected_reversed.get(x.getFinishingAirport()).add(starting_airport);
                this.timelines.computeIfAbsent(new Pair<>(starting_airport, x.getFinishingAirport()), k -> new ArrayList<>());
                this.timelines.get(new Pair<>(starting_airport, x.getFinishingAirport())).add(x);
            }
        }
    }
    public RouteInformation findOptimalTimeTimeline(int starting_airport, int finishing_airport, long starting_time) {
        long starts = starting_time;
        starting_time = starting_time % SECONDS_IN_WEEK;
        Set<Integer> a = new HashSet<>();

        for (var x : time.entrySet()) {
            x.setValue(1000000007L * 1000000007L);
        }
        for (var x : prices.entrySet()) {
            x.setValue(1000000007L * 1000000007L);
        }
        time.put(starting_airport, starting_time);
        BinaryHeap available = new BinaryHeap(new ArrayList<>());
        available.add(new Pair<>((long)starting_airport, starting_airport));
        while (available.size() > 0) {
            int u = available.removeMin().getValue();
            if (a.contains(u)) continue;
            a.add(u);
            if (connected.get(u) == null) continue;
            for (Integer v : connected.get(u)) {
                if (!a.contains(v)) {
                    Timeline optimal;
                    long flight_time = SECONDS_IN_WEEK * 2;
                    for (Timeline x : timelines.get(new Pair<>(u, v))) {
                        long starts_in = time.get(u) % SECONDS_IN_WEEK;
                        long starts_time = x.getStartingTime();
                        if (starts_time - starts_in < 0) {
                            starts_time += SECONDS_IN_WEEK;
                        }
                        if (flight_time > starts_time - starts_in) {
                            optimal = x;
                            flight_time = starts_time - starts_in + x.getFlightTime();
                        }
                    }
                    available.add(new Pair<>(flight_time, v));
                    time.put(v, Math.min(time.get(v), time.get(u) + flight_time));
                }
            }
        }
        if (time.get(finishing_airport) == null || time.get(finishing_airport) >= 1000000007L * 1000000007L) {
            return null;
        }
        ArrayList<Timeline> transfers = new ArrayList<>();
        int current_airport = finishing_airport;
        while (current_airport != starting_airport) {
            Timeline optimal; boolean isFound = false;
            for (Integer v : connected_reversed.get(current_airport)) {
                for (Timeline x : timelines.get(new Pair<>(v, current_airport))) {
                    if ((time.get(current_airport) - x.getFlightTime() - x.getStartingTime()) % SECONDS_IN_WEEK == 0) {
                        transfers.add(x);
                        isFound = true;
                        current_airport = v;
                        break;
                    }
                }
                if (isFound) break;
            }
        }
        Collections.reverse(transfers);
        return new RouteInformation(transfers, starting_airport, finishing_airport, starts);
    }
    public RouteInformation findOptimalPriceTimeline(int starting_airport, int finishing_airport, long starting_time) {
        long starts = starting_time;
        starting_time = starting_time % SECONDS_IN_WEEK;
        Set<Integer> a = new HashSet<>();
        for (var x : time.entrySet()) {
            x.setValue(1000000007L * 1000000007L);
        }
        for (var x : prices.entrySet()) {
            x.setValue(1000000007L * 1000000007L);
        }
        prices.put(starting_airport, 0L);
        time.put(starting_airport, starting_time);
        BinaryHeap available = new BinaryHeap(new ArrayList<>());
        available.add(new Pair<>(0L, starting_airport));
        while (available.size() > 0) {
            int u = available.removeMin().getValue();
            if (a.contains(u)) continue;
            a.add(u);
            if (connected.get(u) == null) continue;
            for (Integer v : connected.get(u)) {
                if (!a.contains(v)) {
                    long min_flight_price = 1000000007L * 1000000007L; long flight_time = SECONDS_IN_WEEK * 2;
                    for (Timeline x : timelines.get(new Pair<>(u, v))) {
                        long starts_in = time.get(u) % SECONDS_IN_WEEK;
                        long starts_time = x.getStartingTime();
                        if (starts_time - starts_in < 0) {
                            starts_time += SECONDS_IN_WEEK;
                        }
                        if (min_flight_price > x.getPrice()) {
                            flight_time = starts_time - starts_in + x.getFlightTime();
                            min_flight_price = (long)Math.floor(x.getPrice() * 100);
                        }
                    }
                    available.add(new Pair<>(min_flight_price, v));
                    if (prices.get(v) >= prices.get(u) + min_flight_price) {
                        time.put(v, time.get(u) + flight_time);
                        prices.put(v, prices.get(u) + min_flight_price);
                    }
                }
            }
        }
        if (time.get(finishing_airport) == null || time.get(finishing_airport) >= 1000000007L * 1000000007L) {
            return null;
        }
        ArrayList<Timeline> transfers = new ArrayList<>();
        int current_airport = finishing_airport;
        while (current_airport != starting_airport) {
            Timeline optimal; boolean isFound = false;
            for (Integer v : connected_reversed.get(current_airport)) {
                for (Timeline x : timelines.get(new Pair<>(v, current_airport))) {
                    if ((time.get(current_airport) - x.getFlightTime() - x.getStartingTime()) % SECONDS_IN_WEEK == 0) {
                        transfers.add(x);
                        isFound = true;
                        current_airport = v;
                        break;
                    }
                }
                if (isFound) break;
            }
        }
        Collections.reverse(transfers);
        return new RouteInformation(transfers, starting_airport, finishing_airport, starts);
    }
    public RouteInformation findOptimalTimeline(int starting_airport, int finishing_airport, long starting_time) {
        long starts = starting_time;
        starting_time = starting_time % SECONDS_IN_WEEK;
        for (var x : time.entrySet()) {
            x.setValue(1000000007L * 1000000007L);
        }
        for (var x : prices.entrySet()) {
            x.setValue(1000000007L * 1000000007L);
        }
        Set<Integer> a = new HashSet<>();
        prices.put(starting_airport, 0L);
        time.put(starting_airport, starting_time);
        BinaryHeap available = new BinaryHeap(new ArrayList<>());
        available.add(new Pair<>(0L, starting_airport));
        while (available.size() > 0) {
            int u = available.removeMin().getValue();
            if (a.contains(u)) continue;
            a.add(u);
            if (connected.get(u) == null) continue;
            for (Integer v : connected.get(u)) {

                if (!a.contains(v)) {
                    long min_flight_price = 1000000007L * 1000000007L; long flight_time = SECONDS_IN_WEEK * 2;
                    for (Timeline x : timelines.get(new Pair<>(u, v))) {
                        long starts_in = time.get(u) % SECONDS_IN_WEEK;
                        long starts_time = x.getStartingTime();
                        if (starts_time - starts_in < 0) {
                            starts_time += SECONDS_IN_WEEK;
                        }
                        if (min_flight_price > x.getPrice()) {
                            flight_time = starts_time - starts_in + x.getFlightTime();
                            min_flight_price = (long)Math.floor(x.getPrice() * 100);
                        }
                    }
                    available.add(new Pair<>(min_flight_price + flight_time / 50, v));
                    if (prices.get(v) >= prices.get(u) + min_flight_price + flight_time / 50) {
                        time.put(v, time.get(u) + flight_time);
                        prices.put(v, prices.get(u) + min_flight_price + flight_time / 50);

                    }

                }
            }
        }
        if (time.get(finishing_airport) == null || time.get(finishing_airport) >= 1000000007L * 1000000007L) {
            return null;
        }
        ArrayList<Timeline> transfers = new ArrayList<>();
        int current_airport = finishing_airport;

        while (current_airport != starting_airport) {
            Timeline optimal; boolean isFound = false;
            for (Integer v : connected_reversed.get(current_airport)) {
                for (Timeline x : timelines.get(new Pair<>(v, current_airport))) {
                    if ((time.get(current_airport) - x.getFlightTime() - x.getStartingTime()) % SECONDS_IN_WEEK == 0) {
                        transfers.add(x);
                        isFound = true;
                        current_airport = v;
                        break;
                    }
                }
                if (isFound) break;
            }
        }
        Collections.reverse(transfers);
        return new RouteInformation(transfers, starting_airport, finishing_airport, starts);
    }
    public Set<RouteInformation> findSomeWays(int starting_airport, int finishing_airport, long starting_time) {
        Set<RouteInformation> direct_routes = new HashSet<>();
        if (timelines.get(new Pair<>(starting_airport, finishing_airport)) != null) {
            for (Timeline x : timelines.get(new Pair<>(starting_airport, finishing_airport))) {
                long starts_in = starting_time % SECONDS_IN_WEEK;
                long starts_time = x.getStartingTime();
                if (starts_time - starts_in < 0) {
                    starts_time += SECONDS_IN_WEEK;
                }
                long flight_time = starts_time - starts_in + x.getFlightTime();
                time.put(finishing_airport, starting_time + flight_time);
                prices.put(finishing_airport, (long)(x.getPrice() * 100));
                ArrayList<Timeline> transfers = new ArrayList<>();
                transfers.add(x);
                direct_routes.add(new RouteInformation(transfers, starting_airport, finishing_airport, starting_time));
            }
        }
        if (connected.get(starting_airport) != null) {
            for (Integer v : connected.get(starting_airport)) {
                if (timelines.get(new Pair<>(starting_airport, v)) != null && timelines.get(new Pair<>(v, finishing_airport)) != null) {
                    for (Timeline x : timelines.get(new Pair<>(starting_airport, v))) {
                        for (Timeline y : timelines.get(new Pair<>(v, finishing_airport))) {
                            if (direct_routes.size() > 50) return direct_routes;
                            long starts_in = starting_time % SECONDS_IN_WEEK;
                            long starts_time = x.getStartingTime() % SECONDS_IN_WEEK;
                            if (starts_time - starts_in < 0) {
                                starts_time += SECONDS_IN_WEEK;
                            }
                            long flight_time = starts_time - starts_in + x.getFlightTime();
                            time.put(v, flight_time);
                            prices.put(v, (long) (x.getPrice() * 100));

                            starts_in = time.get(v) % SECONDS_IN_WEEK;
                            starts_time = y.getStartingTime() % SECONDS_IN_WEEK;
                            if (starts_time - starts_in < 0) {
                                starts_time += SECONDS_IN_WEEK;
                            }
                            flight_time = starts_time - starts_in + y.getFlightTime();
                            time.put(finishing_airport, time.get(v) + flight_time);
                            prices.put(finishing_airport, (long) (x.getPrice() * 100));
                            ArrayList<Timeline> transfers = new ArrayList<>();
                            transfers.add(x);
                            transfers.add(y);
                            direct_routes.add(new RouteInformation(transfers, starting_airport, finishing_airport, starting_time));
                        }
                    }
                }
            }
        }
        return direct_routes;
    }
}
