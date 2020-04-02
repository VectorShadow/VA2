package world.item;

public class Text extends StackableItem {

    //todo - languages, difficulty, whatever else we need here?

    public Text(ItemTemplate it) {
        super(it);
    }
    private Text(Text t) {
        this((ItemTemplate)t.getTemplate());
    }

    @Override
    public Item clone() {
        return new Text(this);
    }

    @Override
    public double getIntegrityPercent() {
        return 1.0;
    }
}
