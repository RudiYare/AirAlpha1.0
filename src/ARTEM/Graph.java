

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
    private final long time_error = SECONDS_IN_WEEK * 3 / 7 + SECONDS_IN_WEEK / 56;
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

    Set<RouteInformation> optimal_routes = new HashSet<>();
    Set<RouteInformation> fastest_routes = new HashSet<>();
    Set<RouteInformation> cheapest_routes = new HashSet<>();

    private void getWayBack(ArrayList<Timeline> prev, ArrayList<Pair<Long, Long>> prev_times, int u, int starting_airport, int finishing_airport, long starting_time, int type) {
        System.out.println("p " + u + " " + starting_airport);
        ArrayList<Timeline> current = new ArrayList<>(prev);
        ArrayList<Pair<Long, Long>> curr_times = new ArrayList<>(prev_times);
        if (prev.size() > 0 && prev.get(prev.size() - 1).getStartingAirport() == starting_airport) {
            Collections.reverse(current);
            if (type == 1) {
                optimal_routes.add(new RouteInformation(current, starting_airport, finishing_airport, curr_times, starting_time - time_error));
            } else if (type == 2) {
                fastest_routes.add(new RouteInformation(current, starting_airport, finishing_airport, curr_times, starting_time - time_error));
            } else if (type == 3) {
                cheapest_routes.add(new RouteInformation(current, starting_airport, finishing_airport, curr_times, starting_time - time_error));
            }
            return;
        }
        for (Integer v : connected_reversed.get(u)) {
            for (Timeline x : timelines.get(new Pair<>(v, u))) {
                if ((time.get(u) - x.getFlightTime() - x.getStartingTime()) % SECONDS_IN_WEEK == 0) {
                    current.add(x);
                    curr_times.add(new Pair<>((time.get(u) - x.getFlightTime() - time_error), (time.get(u) - time_error)));
                    getWayBack(current, curr_times, v, starting_airport, finishing_airport, starting_time, type);
                    current.remove(current.size() - 1);
                    curr_times.remove(curr_times.size() - 1);
                }
            }
        }
    }

    public ArrayList<RouteInformation> findOptimalTimeTimeline(int starting_airport, int finishing_airport, long starting_time) {
        starting_time = (starting_time + time_error);
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
                    long flight_time = SECONDS_IN_WEEK * 2;
                    for (Timeline x : timelines.get(new Pair<>(u, v))) {
                        long starts_in = time.get(u) % SECONDS_IN_WEEK;
                        long starts_time = x.getStartingTime();
                        if (starts_time - starts_in < 0) {
                            starts_time += SECONDS_IN_WEEK;
                        }
                        if (flight_time > starts_time - starts_in + (x.getFlightTime() + SECONDS_IN_WEEK) % SECONDS_IN_WEEK) {
                            flight_time = starts_time - starts_in + (x.getFlightTime() + SECONDS_IN_WEEK) % SECONDS_IN_WEEK;
                        }
                    }
                    available.add(new Pair<>(time.get(u) + flight_time, v));
                    time.put(v, Math.min(time.get(v), time.get(u) + flight_time));
                }
            }
        }
        if (time.get(finishing_airport) == null || time.get(finishing_airport) >= 1000000007L * 1000000007L) {
            return null;
        }

        getWayBack(new ArrayList<>(), new ArrayList<>(), finishing_airport, starting_airport, finishing_airport, starting_time, 2);
        System.out.println(fastest_routes.size());
        ArrayList<RouteInformation> routes = new ArrayList<>();
        routes.addAll(fastest_routes);
        return routes;
    }
    public ArrayList<RouteInformation> findOptimalPriceTimeline(int starting_airport, int finishing_airport, long starting_time) {
        starting_time = (starting_time + time_error);
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
        getWayBack(new ArrayList<>(), new ArrayList<>(), finishing_airport, starting_airport, finishing_airport, starting_time, 3);
        System.out.println(cheapest_routes.size());
        ArrayList<RouteInformation> routes = new ArrayList<>();
        routes.addAll(cheapest_routes);
        return routes;
    }
    public ArrayList<RouteInformation> findOptimalTimeline(int starting_airport, int finishing_airport, long starting_time) {
        starting_time = (starting_time + time_error);
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

        getWayBack(new ArrayList<>(), new ArrayList<>(), finishing_airport, starting_airport, finishing_airport, starting_time, 1);
        System.out.println(optimal_routes.size());
        ArrayList<RouteInformation> routes = new ArrayList<>();
        routes.addAll(optimal_routes);
        return routes;
    }

}
