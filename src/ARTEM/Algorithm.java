package ARTEM;



import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Algorithm {

    String s;
    int a;
    int b;


    public Algorithm(String s) {
        this.s = normalizeString(s);
    }

    public String normalizeString(String s) {
        s = s.toLowerCase(Locale.ROOT);
        s = s.replace('щ', 'ш');
        s = s.replace('ё', 'е');
        s = s.replace('ы', 'и');
        s = s.replace('й', 'и');
        s = s.replace('и', 'е');
        s = s.replace('а', 'o');
        return s;
    }

    public Object[] maxSubstring(String substring) {
        int max_length = 0;
        int starting_position = 0;
        for (int i = 1; i < substring.length(); i++) {
            String current_substring = substring.substring(0, i);
            if (this.s.contains(current_substring)) {
                max_length = i;
                starting_position = s.indexOf(current_substring);
            }
        }
        return new Object[] {max_length, starting_position};
    }


}
