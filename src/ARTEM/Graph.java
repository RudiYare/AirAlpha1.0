

package ARTEM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {
    private Map<Integer, ArrayList<Integer>> connected = new HashMap();
    private ArrayList<ArrayList<Double>> prices;
    private Map<Integer, Boolean> used = new HashMap();

    public Graph(Map<Integer, ArrayList<Timeline>> timelines) {
        Iterator var2 = timelines.entrySet().iterator();

        while(true) {
            while(var2.hasNext()) {
                Entry<Integer, ArrayList<Timeline>> entry = (Entry)var2.next();
                Integer starting_airport = (Integer)entry.getKey();
                this.used.put(starting_airport, false);
                ArrayList<Timeline> actual_timelines = (ArrayList)entry.getValue();
                if (this.connected.containsKey(starting_airport)) {
                    Iterator var9 = actual_timelines.iterator();

                    while(var9.hasNext()) {
                        Timeline x = (Timeline)var9.next();
                        ((ArrayList)this.connected.get(starting_airport)).add(x.getFinishingAirport());
                        this.used.put(x.getFinishingAirport(), false);
                    }
                } else {
                    ArrayList<Integer> current_airports = new ArrayList();
                    Iterator var7 = actual_timelines.iterator();

                    while(var7.hasNext()) {
                        Timeline x = (Timeline)var7.next();
                        current_airports.add(x.getFinishingAirport());
                        this.used.put(x.getFinishingAirport(), false);
                    }

                    this.connected.put(starting_airport, current_airports);
                }
            }

            return;
        }
    }

    private void dfs(int current_airport) {
        if (!(Boolean)this.used.get(current_airport)) {
            this.used.put(current_airport, true);
            if (this.connected.get(current_airport) != null) {
                Iterator var2 = ((ArrayList)this.connected.get(current_airport)).iterator();

                while(var2.hasNext()) {
                    int v = (Integer)var2.next();
                    this.dfs(v);
                }
            }

        }
    }

    public boolean isReachable(int starting_airport, int finishing_airport) {
        this.dfs(starting_airport);
        return (Boolean)this.used.get(finishing_airport);
    }
}
