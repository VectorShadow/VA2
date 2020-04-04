package world.lore.language;

import main.Session;

import java.io.Serializable;
import java.util.Random;

public enum Language implements Serializable {
    YSIAN("Ysian"),
    OLD_WYRDEN("Old Wyrden"),
    LOW_NUNAK("Low Nunak"),
    MUESE("Muese"),
    HIGH_NUNAK("High Nunak"),
    ERDUK("Erduk"),
    DARK_NUNAK("Dark Nunak"),
    HYBOREAN("Hyborean"),
    THULIAN("Thulian"),
    DRACONIAN("Draconian"),
    AGARTHAN("Agarthan"),
    TRANSGALACTIC("Transgalactic"),
    LUNAR("Lunar"),
    SYLFAN("Sylfan"),
    CENTAURAN("Centauran"),
    TYPHONIAN("Typhonian"),
    OPHIDIAN("Ophidian"),
    VRILLISH("Vrillish"),
    STARSCRAWL("Starscrawl");

    final String NAME;

    Language(String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public double learn(double currentKnowledge, int learningPower) {
        if (currentKnowledge >= 1.0 || ordinal() == 0) return 1.0; //if we know this language completely, do nothing
        Random rng = Session.getRNG();
        final double CURVE_FACTOR = 0.5;
        //create a learning curve such that gains are slowest when you know very little, and when you know almost everything
        double learningCurve = Math.abs(currentKnowledge - CURVE_FACTOR);
        //limit the gain per successful attempt to a value from 0.1% in the best case to ~0.0025% in the worst case
        double gain = 0.01; //gain at least 1% for trying.
        int difficulty = ordinal(); //difficulty corresponds directly to the ordinal value of this language
        for (int attempts = 0; attempts < learningPower; ++attempts) { //make one attempt to increase per learning power
            if (rng.nextInt(difficulty) + 1 == difficulty) { // 1 in [difficulty] attempts succeeds
                //on success, gain the higher of a quarter percent and a random value
                //whose potential ranges from 0% at the ends of the learning curve up to 5% at the center.
                gain += Math.max(0.0025, (CURVE_FACTOR * rng.nextDouble() - learningCurve) / 10.0);
            }
        }
        return Math.min(1.0, currentKnowledge + gain);
    }
}
