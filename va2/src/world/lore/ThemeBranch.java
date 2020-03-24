package world.lore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ThemeBranch implements Iterable<LoreLeaf>, Serializable {
    private final String name;
    private ArrayList<LoreLeaf> loreLeaves;

    public ThemeBranch(String name) {
        this.name = name;
        loreLeaves = new ArrayList<>();
    }

    public void add(LoreLeaf loreLeaf) {
        loreLeaves.add(loreLeaf);
    }

    public LoreLeaf get(int index) {
        return loreLeaves.get(index);
    }

    public String name() {
        return name;
    }

    public int size() {
        return loreLeaves.size();
    }

    @Override
    public Iterator<LoreLeaf> iterator() {
        return loreLeaves.iterator();
    }
}
