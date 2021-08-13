package ARTEM;

import java.lang.reflect.Array;
import java.util.*;

class Tree {
    public ArrayList<Tree> children;
    public Tree parent;
    public String title;
    public int ID;

    public Tree(Tree parent, String title) {
        this.parent = parent;
        this.title = title;
        this.children = new ArrayList<>();
    }

}

public class Network {

    private Set<Tree> countries;
    private Map<Integer, Tree> airports;
    private Map<Integer, Timeline> timelines;

    public Network() {
        countries = new HashSet<>();
        airports = new HashMap<>();
        timelines = new HashMap<>();
    }

    public boolean addNewAirport(String title, String city, String country) {

        Tree actual_country = new Tree(null, country);
        for (Tree t : countries) {
            if (t.title.equals(country)) {
                actual_country = t;
            }
        }

        countries.add(actual_country);

        Tree actual_city = new Tree(actual_country, city); boolean isFound = false;


        for (Tree t : actual_country.children) {
            if (t.title.equals(city)) {
                actual_city = t;
                isFound = true;
            }
        }

        if (!isFound) {
            actual_country.children.add(actual_city);
        }

        Tree actual_title = new Tree(actual_city, title);
        isFound = false;
        for (Tree t : actual_city.children) {
            if (t.title.equals(title)) {
                actual_title = t;
                isFound = true;
            }
        }

        if (!isFound) {
            int ID;
            do {
                ID = (int)(Math.random() * 100000000);
            } while (airports.containsKey(ID));
            actual_title.ID = ID;
            airports.put(ID, actual_title);
            actual_city.children.add(actual_title);
        }

        return (!isFound);

    }
    public ArrayList<String> getAllCountries() {
        ArrayList<String> titles = new ArrayList<>();
        for (Tree k : countries) {
            titles.add(k.title);
        }
        return titles;
    }
    public ArrayList<String> getAllCities(String country) {
        ArrayList<String> titles = new ArrayList<>();
        Tree actual_country = new Tree(null, country); boolean isFound = false;
        for (Tree k : countries) {
            if (k.title.equals(country)) {
                actual_country = k;
                isFound = true;
            }
        }
        if (isFound) {
            for (Tree k : actual_country.children) {
                titles.add(k.title);
            }
        }
        return titles;
    }
    public ArrayList<String> getAllTitles(String country, String city) {

        ArrayList<String> titles = new ArrayList<>();
        Tree actual_country = new Tree(null, country); boolean isFound = false;
        for (Tree k : countries) {
            if (k.title.equals(country)) {
                actual_country = k;
                isFound = true;
            }
        }
        if (isFound) {
            isFound = false;
            Tree actual_city = new Tree(null, country);
            for (Tree k : actual_country.children) {
                if (k.title.equals(city)) {
                    actual_city = k;
                    isFound = true;
                }
            }
            if (isFound) {
                for (Tree k : actual_city.children) {
                    titles.add(k.title);
                }
            }
        }

        return titles;

    }
    public int getIDByParams(String country, String city, String title) {
        int ID = -1;
        Tree actual_country = new Tree(null, country); boolean isFound = false;
        for (Tree k : countries) {
            if (k.title.equals(country)) {
                actual_country = k;
                isFound = true;
            }
        }
        if (isFound) {
            isFound = false;
            Tree actual_city = new Tree(null, country);
            for (Tree k : actual_country.children) {
                if (k.title.equals(city)) {
                    actual_city = k;
                    isFound = true;
                }
            }
            if (isFound) {
                isFound = false;
                Tree actual_title = new Tree(null, country);
                for (Tree k : actual_city.children) {
                    if (k.title.equals(title)) {
                        actual_title = k;
                        isFound = true;
                    }
                }
                if (isFound) {
                    ID = actual_title.ID;
                }
            }
        }
        return ID;
    }

    public boolean addNewTimeline(int starting_airport, int finishing_airport, int starting_time, int flight_time, int price) {
        Timeline flight = new Timeline(starting_airport, finishing_airport, starting_time, flight_time, price);
        if (timelines.containsValue(flight)) {
            return false;
        } else {
            timelines.put(starting_airport, flight);
            return true;
        }
    }



}
