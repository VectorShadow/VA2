package combat.melee.forms;

import combat.melee.AttackTactic;
import combat.melee.DefenseTactic;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;

import java.awt.*;

import static combat.melee.AttackTactic.*;
import static combat.melee.DefenseTactic.*;

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
                            .addElement(new Pair<>(0.25, BLOW))
                            .addElement(new Pair<>(0.4, PROBE))
                            .addElement(new Pair<>(0.55, FEINT))
                            .addElement(new Pair<>(0.65, ANTICIPATE))
                            .build()
            ),
            new Continuum<>(
                    DefenseTactic.EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.3, DEFLECT))
                            .addElement(new Pair<>(0.55, RIPOSTE))
                            .addElement(new Pair<>(0.65, BRACE))
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
                            .addElement(new Pair<>(0.33, BLOW))
                            .addElement(new Pair<>(0.43, PROBE))
                            .addElement(new Pair<>(0.50, FEINT))
                            .build()
            ),
            new Continuum<>(
                    DefenseTactic.EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.05, RIPOSTE))
                            .addElement(new Pair<>(0.25, BRACE))
                            .build()
            ),
            new int[] {0,5,0,0,0}
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
                            .addElement(new Pair<>(0.4, BLOW))
                            .addElement(new Pair<>(0.6, ANTICIPATE))
                            .build()
            ),
            new Continuum<>(
                    DefenseTactic.EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.6, BRACE))
                            .addElement(new Pair<>(0.85, IGNORE))
                            .build()
            ),
            new int[] {3,0,0,0,8}
    );
    /**
     * A form for striking type serpents.
     */
    public static final Form STRIKING_SERPENT = new Form(
            "A combat style used by striking serpents.",
            "<Striking Serpent>",
            Color.WHITE, //todo - Color Standards!
            null,
            null,
            new Continuum<>(
                    STRIKE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.25, PROBE))
                            .addElement(new Pair<>(0.4, ANTICIPATE))
                            .build()
            ),
            new Continuum<>(
                    EVADE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.25, RIPOSTE))
                            .addElement(new Pair<>(0.35, BRACE))
                            .addElement(new Pair<>(0.4, IGNORE))
                            .build()
            ),
            new int[]{0, 5, 10, 0, 0}
    );
    /**
     * A form for shambling undead.
     */
    public static final Form SHAMBLER = new Form(
            "A combat style used by the shambling undead",
            "<Shambler>",
            Color.WHITE, //todo - Color standards!
            null,
            null,
            new Continuum<>(
                    BLOW,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.35, STRIKE))
                            .build()
            ),
            new Continuum<>(
                    IGNORE,
                    ArrayListBuilder
                            .initialize()
                            .addElement(new Pair<>(0.35, BRACE))
                            .build()
            ),
            new int[]{0, 0, 0, 0, 5}
    );
}
