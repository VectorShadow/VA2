package world.lore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LoreTree implements Serializable {
    private ArrayList<ThemeBranch> themeBranches;

    public LoreTree() {
        themeBranches = new ArrayList<>();
    }

    public void add(ThemeBranch themeBranch) {
        themeBranches.add(themeBranch);
    }

    public LoreLeaf get(int themeIndex, int loreIndex) {
        return themeBranches.get(themeIndex).get(loreIndex);
    }

    public int size() {
        return themeBranches.size();
    }

    public ThemeBranch getBranch(int branchIndex) {
        return themeBranches.get(branchIndex);
    }
}
