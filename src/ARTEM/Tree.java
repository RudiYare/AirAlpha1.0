package ARTEM;

import java.util.ArrayList;

public class Tree {
    public ArrayList<Tree> children;
    public Tree parent;
    public String title;
    public Double x;
    public Double y;
    public int ID;

    public Tree(Tree parent, String title) {
        this.parent = parent;
        this.title = title;
        this.children = new ArrayList();
        this.x = null;
        this.y = null;
    }

    public Tree(Tree parent, String title, double x, double y) {
        this.parent = parent;
        this.title = title;
        this.children = new ArrayList();
        this.x = x;
        this.y = y;
    }
}

