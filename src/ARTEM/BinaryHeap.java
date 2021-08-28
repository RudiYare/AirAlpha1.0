package ARTEM;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BinaryHeap {
    public ArrayList<Pair<Long, Integer>> heap;
    public Set<Pair<Long, Integer>> used;
    public int size;
    public BinaryHeap(ArrayList<Pair<Long, Integer>> array) {
        this.heap = new ArrayList<>();
        this.used = new HashSet<>();
        for (Pair<Long, Integer> x : array) {
            this.add(x);
        }
    }
    public void add(Pair<Long, Integer> el) {
        if (used.contains(el)) return;
        used.add(el);
        heap.add(el);
        size++;
        int current = size - 1;
        while (current > 0 && heap.get((current - 1) / 2).getKey() > heap.get(current).getKey()) {
            Collections.swap(this.heap, (current - 1) / 2, current);
            current = (current - 1) / 2;
        }
    }
    public int size() {return this.size; }
    public Pair<Long, Integer> getMin() {
        return heap.get(0);
    }
    public Pair<Long, Integer> removeMin() {
        if (size == 0) {
            return new Pair<>(-1L, -1);
        }
        size--;
        Pair<Long, Integer> result = this.heap.get(0);
        Collections.swap(this.heap, size, 0);
        this.heap.remove(size);
        this.used.remove(result);
        int current = 0;
        while (2 * current + 1 < size) {
            int min_index = 2 * current + 1;
            if (2 * current + 2 < size) {
                if (this.heap.get(2 * current + 2).getKey() < this.heap.get(2 * current + 1).getKey()) {
                    min_index++;
                }
            }
            if (this.heap.get(current).getKey() > this.heap.get(min_index).getKey()) {
                Collections.swap(this.heap, current, min_index);
                current = min_index;
            } else {
                break;
            }
        }
        return result;
    }
}