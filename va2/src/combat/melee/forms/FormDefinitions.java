package combat.melee.forms;

import combat.melee.AttackTactic;
import combat.melee.DefenseTactic;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;

import java.awt.*;

public class FormDefinitions {
    /**
     * An untrained melee weapon form, usable with any style and weapon class.
     * Offers 35% Strike, 25% Blow, 15% Probe, 15% Feint, and 10% Anticipate on attack,
     * and 35% Evade, 30% Deflect, 25% Riposte, 10% Brace, and 0% Ignore on defence.
     */
    public static final Form UNTRAINED = new Form(
            "<Untrained>",
            "untrained melee combat",
            Color.WHITE, //todo - Color Standards!
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
            ),
            new int[] {0,0,0,0,0}
    );
    /**
     * The default form used by small animals.
     * They focus mostly on strikes or heavier blows, with a few probes and feints.
     * Defensively, the priority is evasion, occasionally bracing for attacks, and rarely attempting a counter.
     * All small animals using this form get a slight bonus to evasion.
     */
    public static final Form SMALL_BEAST = new Form(
            "A combat style used by smaller animals against larger opponents.",
            "<Small Beast>",
            Color.WHITE, //todo - Color Standards!
            null,
            null,
            new Continuum<>(
                    AttackTactic.STRIKE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.33, AttackTactic.BLOW))
                            .addElement(new Pair<>(0.43, AttackTactic.PROBE))
                            .addElement(new Pair<>(0.50, AttackTactic.FEINT))
                            .build()
            ),
            new Continuum<>(
                    DefenseTactic.EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.05, DefenseTactic.RIPOSTE))
                            .addElement(new Pair<>(0.25, DefenseTactic.BRACE))
                            .build()
            ),
            new int[] {0,25,0,0,0}
    );
    /**
     * The default form used by large animals.
     * They split strikes and heavy blows, occasionally attempting to anticipate their target's reaction.
     * Defensively, they prefer to brace for and shrug off incoming attacks, but occasionally attempt evasion, or
     * simply ignore them.
     * All large animals using this form get a slight bonus to accuracy and a moderate bonus to strength.
     */
    public static final Form LARGE_BEAST = new Form(
            "A combat style used by larger animals against smaller opponents.",
            "<Large Beast>",
            Color.WHITE, //todo - Color Standards!
            null,
            null,
            new Continuum<>(
                    AttackTactic.STRIKE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.4, AttackTactic.BLOW))
                            .addElement(new Pair<>(0.6, AttackTactic.ANTICIPATE))
                            .build()
            ),
            new Continuum<>(
                    DefenseTactic.EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.6, DefenseTactic.BRACE))
                            .addElement(new Pair<>(0.85, DefenseTactic.IGNORE))
                            .build()
            ),
            new int[] {10,0,0,0,35}
    );
}
