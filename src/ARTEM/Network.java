package ARTEM;

import java.lang.reflect.Array;
import java.util.*;

class Tree {
    public ArrayList<Tree> children;
    public Tree parent;
    public String title;

    public Tree(Tree parent, String title) {
        this.parent = parent;
        this.title = title;
        this.children = new ArrayList<>();
    }

}

public class Network {

    private Set<Tree> countries;

    public Network() {
        countries = new HashSet<>();
    }

    public boolean addNewAirport(String title, String city, String country) {

        Tree actual_country = new Tree(null, country);
        for (Tree t : countries) {
            if (t.title.equals(country)) {
                actual_country = t;
            }
        }

        countries.add(actual_country);



        Tree actual_city = new Tree(actual_country, city);
        for (Tree t : actual_country.children) {
            if (t.title.equals(city)) {
                actual_city = t;
            }
        }
        actual_country.children.add(actual_city);

        Tree actual_title = new Tree(actual_city, title);
        boolean isFound = false;
        for (Tree t : actual_city.children) {
            if (t.title.equals(title)) {
                actual_title = t;
                isFound = true;
            }
        }

        if (!isFound) {
            actual_city.children.add(actual_title);
        }

        System.out.print(actual_country.title);
        System.out.print(" ");

        System.out.print(actual_city.title);
        System.out.print(" ");

        System.out.print(actual_title.title);
        System.out.println(" ");

        return (!isFound);

    }

    public void printCountries() {
        for (Tree country : countries) {
            System.out.println(country.title);
        }
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

}
