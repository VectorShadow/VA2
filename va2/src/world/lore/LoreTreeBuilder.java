package world.lore;

public class LoreTreeBuilder {
    private LoreTree lockTree;
    private LoreTree stringTree;
    private ThemeBranch lockTheme;
    private ThemeBranch stringTheme;

    private LoreTreeBuilder() {
        lockTree = new LoreTree();
        stringTree = new LoreTree();
    }

    public static LoreTreeBuilder plant() {
        return new LoreTreeBuilder();
    }

    public LoreTreeBuilder addTheme(String themeName) {
        lockTheme = new ThemeBranch(themeName);
        stringTheme = new ThemeBranch(themeName);
        return this;
    }
    public LoreTreeBuilder addLeaf(String name, String lore) {
        lockTheme.add(new LockLeaf());
        stringTheme.add(new StringLeaf(name, lore));
        return this;
    }
    public LoreTreeBuilder growTheme() {
        lockTree.add(lockTheme);
        stringTree.add(stringTheme);
        return this;
    }
    public LoreTree getLockTree() {
        return lockTree;
    }

    public LoreTree getStringTree() {
        return stringTree;
    }
}
