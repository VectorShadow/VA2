package world.lore;

import util.Format;

import java.io.Serializable;

public class LanguageKnowledge implements Serializable {
    double[] knowledge = new double[Language.values().length];

    public LanguageKnowledge() {
        for (int i = 0; i < knowledge.length; ++i) {
            knowledge[i] = 0.0;
        }
        knowledge[Language.YSIAN.ordinal()] = 1.0;
        knowledge[Language.OLD_WYRDEN.ordinal()] = 0.4;
        knowledge[Language.LOW_NUNAK.ordinal()] = 0.15;
        knowledge[Language.HIGH_NUNAK.ordinal()] = 0.05;
    }

    public double getKnowledge(Language l) {
        return knowledge[l.ordinal()];
    }
    public void setKnowledge(Language l, double d) {
        knowledge[l.ordinal()] = d;
    }

    @Override
    public String toString() {
        String s = "Known Languages: \n";
        double kPct;
        for (Language language : Language.values()) {
            kPct = knowledge[language.ordinal()];
            s +=
                    "\n" +
                            (kPct > 0.0 ? language.getName() : "<undiscovered>") + ": " +
                            Format.percent(kPct * 100.0, 1);
        }
        return s;
    }
}
