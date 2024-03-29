//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ARTEM;

import javafx.util.Pair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class Network {
    private Set<Tree> countries;
    private Map<Integer, Tree> airports;
    private Map<Integer, ArrayList<Timeline>> timelines;

    public Network() {
        countries = new HashSet();
        airports = new HashMap();
        timelines = new HashMap();
    }

    public boolean addNewAirport(String title, String city, String country, double x, double y) {
        Tree actual_country = new Tree((Tree)null, country, x, y);
        for (var t : this.countries) {
            if (t.title.equals(country)) {
                actual_country = t;
            }
        }

        this.countries.add(actual_country);
        Tree actual_city = new Tree(actual_country, city);
        boolean isFound = false;
        for (var t : actual_country.children) {
            if (t.title.equals(city)) {
                actual_city = t;
                isFound = true;
            }
        }

        if (!isFound) {
            actual_country.children.add(actual_city);
        }

        Tree actual_title = new Tree(actual_city, title, x, y);
        isFound = false;
        for (var t : actual_city.children) {
            if (t.title.equals(title)) {
                actual_title = t;
                isFound = true;
            }
        }

        if (!isFound) {
            int ID;
            do {
                ID = (int)(Math.random() * 1.0E8D);
            } while(this.airports.containsKey(ID));

            actual_title.ID = ID;
            this.airports.put(ID, actual_title);
            actual_city.children.add(actual_title);
        }
        System.out.println(title + " " + actual_title.ID);
        return !isFound;
    }
    public boolean addNewAirport(String title, String city, String country, int ID, double x, double y) {
        Tree actual_country = new Tree((Tree)null, country);
        for (var t : this.countries) {
            if (t.title.equals(country)) {
                actual_country = t;
            }
        }

        this.countries.add(actual_country);
        Tree actual_city = new Tree(actual_country, city);
        boolean isFound = false;
        for (var t : actual_country.children) {
            if (t.title.equals(city)) {
                actual_city = t;
                isFound = true;
            }
        }

        if (!isFound) {
            actual_country.children.add(actual_city);
        }

        Tree actual_title = new Tree(actual_city, title, x, y);
        isFound = false;
        for (var t : actual_city.children) {
            if (t.title.equals(title)) {
                actual_title = t;
                isFound = true;
            }
        }

        if (!isFound) {
            actual_title.ID = ID;
            this.airports.put(ID, actual_title);
            actual_city.children.add(actual_title);
        }
        System.out.println(title + " " + actual_title.ID);
        return !isFound;
    }
    public ArrayList<String> getAllCountries() {
        ArrayList<String> titles = new ArrayList();
        Iterator var2 = this.countries.iterator();

        while(var2.hasNext()) {
            Tree k = (Tree)var2.next();
            titles.add(k.title);
        }

        return titles;
    }
    public ArrayList<String> getAllCities(String country) {
        ArrayList<String> titles = new ArrayList();
        Tree actual_country = new Tree((Tree)null, country);
        boolean isFound = false;
        Iterator var5 = this.countries.iterator();

        Tree k;
        while(var5.hasNext()) {
            k = (Tree)var5.next();
            if (k.title.equals(country)) {
                actual_country = k;
                isFound = true;
            }
        }

        if (isFound) {
            var5 = actual_country.children.iterator();

            while(var5.hasNext()) {
                k = (Tree)var5.next();
                titles.add(k.title);
            }
        }

        return titles;
    }
    public ArrayList<Tree> getAllTitles(String country, String city) {
        ArrayList<Tree> titles = new ArrayList();
        Tree actual_country = new Tree((Tree)null, country);
        boolean isFound = false;
        Iterator var6 = this.countries.iterator();

        while(var6.hasNext()) {
            Tree k = (Tree)var6.next();
            if (k.title.equals(country)) {
                actual_country = k;
                isFound = true;
            }
        }

        if (isFound) {
            isFound = false;
            Tree actual_city = new Tree((Tree)null, country);
            Iterator var10 = actual_country.children.iterator();

            Tree k;
            while(var10.hasNext()) {
                k = (Tree)var10.next();
                if (k.title.equals(city)) {
                    actual_city = k;
                    isFound = true;
                }
            }

            if (isFound) {
                var10 = actual_city.children.iterator();

                while(var10.hasNext()) {
                    k = (Tree)var10.next();
                    titles.add(k);
                }
            }
        }

        return titles;
    }
    public int getIDByParams(String title, String city, String country) {
        int ID = -1;
        Tree actual_country = null;
        Iterator var6 = this.countries.iterator();

        Tree actual_title;
        while(var6.hasNext()) {
            actual_title = (Tree)var6.next();
            if (actual_title.title.equals(country)) {
                actual_country = actual_title;
            }
        }

        if (actual_country != null) {
            Tree actual_city = null;
            Iterator var11 = actual_country.children.iterator();

            while(var11.hasNext()) {
                Tree k = (Tree)var11.next();
                if (k.title.equals(city)) {
                    actual_city = k;
                }
            }

            if (actual_city != null) {
                actual_title = null;
                Iterator var12 = actual_city.children.iterator();

                while(var12.hasNext()) {
                    Tree k = (Tree)var12.next();
                    if (k.title.equals(title)) {
                        actual_title = k;
                    }
                }

                if (actual_title != null) {
                    ID = actual_title.ID;
                }
            }
        }
        return ID;
    }
    public Tree getElementByParams(String title, String city, String country) {
        Tree actual_country = null;
        Iterator var5 = this.countries.iterator();

        Tree actual_title;
        while(var5.hasNext()) {
            actual_title = (Tree)var5.next();
            if (actual_title.title.equals(country)) {
                actual_country = actual_title;
            }
        }

        if (actual_country != null) {
            Tree actual_city = null;
            Iterator var10 = actual_country.children.iterator();

            while(var10.hasNext()) {
                Tree k = (Tree)var10.next();
                if (k.title.equals(city)) {
                    actual_city = k;
                }
            }

            if (actual_city != null) {
                actual_title = null;
                Iterator var11 = actual_city.children.iterator();

                while(var11.hasNext()) {
                    Tree k = (Tree)var11.next();
                    if (k.title.equals(title)) {
                        actual_title = k;
                    }
                }

                return actual_title;
            }
        }

        return null;
    }
    public boolean removeAirport(String title, String city, String country) {
        if (this.getIDByParams(title, city, country) != -1) {
            Tree current_airport = this.getElementByParams(title, city, country);
            Tree current_city = current_airport.parent;
            Tree current_country = current_city.parent;
            current_city.children.remove(current_airport);
            if (current_city.children.size() == 0) {
                current_country.children.remove(current_city);
                if (current_country.children.size() == 0) {
                    this.countries.remove(current_country);
                }
            }

            return true;
        } else {
            return false;
        }
    }
    public boolean addNewTimeline(int starting_airport, int finishing_airport, long starting_time, long flight_time, double price) {
        Timeline flight = new Timeline(starting_airport, finishing_airport, starting_time, flight_time, price);
        if (this.timelines.containsKey(starting_airport)) {
            if (((ArrayList)this.timelines.get(starting_airport)).contains(flight)) {
                return false;
            } else {
                ((ArrayList)this.timelines.get(starting_airport)).add(flight);
                return true;
            }
        } else {
            ArrayList<Timeline> actual_timelines = new ArrayList();
            actual_timelines.add(flight);
            this.timelines.put(starting_airport, actual_timelines);
            return true;
        }
    }
    public ArrayList<Timeline> getAllTimelinesInAirport(int starting_airport) {
        return this.timelines.containsKey(starting_airport) ? (ArrayList)this.timelines.get(starting_airport) : new ArrayList();
    }
    public ArrayList<String> search(int starting_airport, int finishing_airport, long starting_time) {
        Graph g = new Graph(this.timelines);
        ArrayList<String> answer = new ArrayList<>();
        if (starting_airport == -1 || finishing_airport == -1) return answer;
        ArrayList<RouteInformation> fastest = g.findOptimalTimeTimeline(starting_airport, finishing_airport, starting_time);
        ArrayList<RouteInformation> cheapest = g.findOptimalPriceTimeline(starting_airport, finishing_airport, starting_time);
        ArrayList<RouteInformation> optimal = g.findOptimalTimeline(starting_airport, finishing_airport, starting_time);
        if (fastest == null) {
            return answer;
        }
        answer.add("" + optimal.size());
        answer.add("" + fastest.size());
        answer.add("" + cheapest.size());
        for (var x : optimal) {
            answer.addAll(x.init(this));
        }
        for (var x : fastest) {
            answer.addAll(x.init(this));
        }
        for (var x : cheapest) {
            answer.addAll(x.init(this));
        }

        return answer;
    }
    public String getAirportInformationAsString(int ID) {
        String answer = "";
        if (airports.containsKey(ID) && airports.get(ID).parent != null && airports.get(ID).parent.parent != null) {
            answer += airports.get(ID).parent.parent.title; answer += ", ";
            answer += airports.get(ID).parent.title; answer += ", ";
            answer += airports.get(ID).title;
            return answer;
        }
        return null;
    }
    public boolean saveData() throws IOException {
        ArrayList<ArrayList<String>> airports = new ArrayList<>();
        Set<Integer> all = new HashSet<>();
        for (var country : getAllCountries()) {
            for (var city : getAllCities(country)) {
                for (var title : getAllTitles(country, city)) {
                    ArrayList<String> airport_data = new ArrayList<>();
                    airport_data.add(country);
                    airport_data.add(city);
                    airport_data.add(title.title);
                    airport_data.add(Integer.toString(getIDByParams(title.title, city, country)));
                    airport_data.add(Double.toString(title.x));
                    airport_data.add(Double.toString(title.y));
                    airports.add(airport_data);
                    all.add(getIDByParams(title.title, city, country));
                }
            }
        }
        Set<Timeline> timelines = new HashSet<>();
        for (var x : all) {
            if (this.timelines.get(x) != null) {
                for (var timeline : this.timelines.get(x)) {
                    timelines.add(timeline);
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(new File("airports.txt")));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("timelines.txt"))) {
            oos.writeObject(this.timelines);
        }
        catch (Exception ex){
            return false;
        }

        pw.println(airports.size());
        for (var x : airports) {
            for (var y : x) {
                pw.println(y);
            }
        }
        pw.close();


        return true;
    }
    public boolean loadData() throws IOException {

        boolean isCorrectData = true;
        try (BufferedReader reader = new BufferedReader(new FileReader("airports.txt"))) {
            String number_of_airports = reader.readLine();
            if (number_of_airports == null) return false;
            for (int airport = 0; airport < Integer.parseInt(number_of_airports); airport++) {
                String country = reader.readLine();
                String city = reader.readLine();
                String title = reader.readLine();
                String ID = reader.readLine();
                String x = reader.readLine();
                String y = reader.readLine();
                if (ID == null) isCorrectData = false;
                this.addNewAirport(title, city, country, Integer.parseInt(ID), Double.parseDouble(x), Double.parseDouble(y));
            }
        } catch (Exception ex) {
            isCorrectData = false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("timelines.txt"))) {
            this.timelines = (Map<Integer, ArrayList<Timeline>>) ois.readObject();
        }
        catch (Exception ex){
            isCorrectData = false;
        }

        if (isCorrectData) {
            return true;
        } else {
            this.timelines = new HashMap<>();
            countries = new HashSet();
            airports = new HashMap();
            return false;
        }

    }
    public void loadRandomData() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(new File("dataset")));
        String line;
        while ((line = input.readLine()) != null) {
            String[] airport = line.split(",");
            boolean k = this.addNewAirport(airport[1].replaceAll("\"", ""), airport[2].replaceAll("\"", ""), airport[3].replaceAll("\"", ""), (int)(Math.random() * 360), (int)(Math.random() * 180));
        }
        ArrayList<Integer> airport_IDs = new ArrayList<>();
        for (var airport : airports.entrySet()) {
            int ID = airport.getKey();
            airport_IDs.add(ID);
        }
        for (int starting_airport = 0; starting_airport < airport_IDs.size(); starting_airport++) {
            for (int finishing_airport = 0; finishing_airport < airport_IDs.size(); finishing_airport++) {
                if (starting_airport != finishing_airport) {
                    final long MV = 1000000007;
                    final long SECONDS_IN_WEEK = 604800000;
                    long rand3 = (int)Math.round(Math.random() * MV);
                    for (int i = 0; i < (int)Math.round(Math.random() * 9); i++) {
                        if (rand3 > MV / 1.5) {
                            this.addNewTimeline(airport_IDs.get(starting_airport),
                                    airport_IDs.get(finishing_airport),
                                    (Math.round(Math.random() * MV) * Math.round(Math.random() * MV)) % SECONDS_IN_WEEK,
                                    (Math.round(Math.random() * MV) * Math.round(Math.random() * MV)) % (SECONDS_IN_WEEK / 14),
                                    Math.round(((3000 * (Math.random() + .5)) + Math.round(Math.random() * MV) / 70000.0) * 100.0) / 100.0);
                        }
                    }
                }
            }
        }
    }
    public void removeAllData() {
        countries = new HashSet();
        airports = new HashMap();
        timelines = new HashMap();
    }
    public ArrayList<ArrayList<Double>> tempPoints() {
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        double k = 641.0 / 173.0; int l = 3;
        double x, ans; ArrayList<Double> r;
                x = 49;
        ans = ((x + l) * k) + 600;
        r = new ArrayList<>();
        r.add(ans); r.add(0.0);
        res.add(r);

        x = 142.5;
        ans = ((x + l) * k) + 600;
        r = new ArrayList<>();
        r.add(ans); r.add(0.0);
        res.add(r);

        x = -81.5;
        ans = ((x + l) * k) + 600;
        r = new ArrayList<>();
        r.add(ans); r.add(0.0);
        res.add(r);

        x = 0;
        ans = ((x + l) * k) + 600;
        r = new ArrayList<>();
        r.add(ans); r.add(0.0);
        res.add(r);

        System.out.println(k + " " + ans);
        return res;
    }

}
