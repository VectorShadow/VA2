package world.lore;

public class LockLeaf extends LoreLeaf {
    private boolean locked = true;

    public boolean isLocked() {
        return locked;
    }

    public void unlock() {
        locked = false;
    }
}
