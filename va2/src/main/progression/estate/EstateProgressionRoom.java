package main.progression.estate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class EstateProgressionRoom implements Iterable<EstateProgressionNode>, Serializable {
    private ArrayList<EstateProgressionNode> nodes = new ArrayList<>();

    public EstateProgressionNode get(int index) {
        return nodes.get(index);
    }

    public void add(EstateProgressionNode epn) {
        nodes.add(epn);
    }

    @Override
    public Iterator<EstateProgressionNode> iterator() {
        return nodes.iterator();
    }
}
