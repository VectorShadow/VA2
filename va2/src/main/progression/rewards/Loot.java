package main.progression.rewards;

import combat.armor.ArmorDefinitions;

public class Loot {

    public static final DropTable PLACEHOLDER = generateDropTable(-1, -1, -1);

    private static DropTable generateDropTable(int theme, int qualityBias, int itemFamily) {
        DropTable table = new DropTable(0.25);
        table.add(ArmorDefinitions.LEATHER_VEST(), 1, 0.0001); //todo - remove this!
        //todo - lots here.
        return table;
    }
}
