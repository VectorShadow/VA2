package combat.melee.forms;

import combat.melee.AttackTactic;
import combat.melee.DefenseTactic;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;

public class FormDefinitions {
    /**
     * An untrained melee weapon form, usable with any style and weapon class.
     * Offers 35% Strike, 25% Blow, 15% Probe, 15% Feint, and 10% Anticipate on attack,
     * and 35% Evade, 30% Deflect, 25% Riposte, 10% Brace, and 0% Ignore on defence.
     */
    public static final Form UNTRAINED = new Form(
            null,
            null,
            new Continuum<>(
                    AttackTactic.STRIKE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.25, AttackTactic.BLOW))
                            .addElement(new Pair<>(0.4, AttackTactic.PROBE))
                            .addElement(new Pair<>(0.55, AttackTactic.FEINT))
                            .addElement(new Pair<>(0.65, AttackTactic.ANTICIPATE))
                            .build()
            ),
            new Continuum<>(
                    DefenseTactic.EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.3, DefenseTactic.DEFLECT))
                            .addElement(new Pair<>(0.55, DefenseTactic.RIPOSTE))
                            .addElement(new Pair<>(0.65, DefenseTactic.BRACE))
                            .build()
            )
    );
}
