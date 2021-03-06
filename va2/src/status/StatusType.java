package status;

import resources.DualityMode;
import resources.chroma.Chroma;
import resources.glyph.Glyph;
import resources.glyph.GlyphBuilder;

import static io.out.GUIManager.*;

public enum StatusType {
    VENOM_0(true, 16, 6, 4, 0.95,
            new boolean[]{true, false, true, false, false, false, false, false, true, false},
            "You feel a burning as venom enters your bloodstream.",
            "You feel venom coursing through your veins.",
            GlyphBuilder.buildGlyph().setDefaults(Chroma.VENOM_GREEN, Chroma.BLUE, '~')
                    .setImageRowAndColumn(GFX_ROW_ADMIN, 8)),
    WEBBED(true, 12, 4, 0, 0.33,
            new boolean[]{true, true, true, true, true, true, false, false, false, false},
            "You are covered in sticky webs.",
            "",
            GlyphBuilder.buildGlyph().setDefaults(Chroma.BLACK, Chroma.WHITE, '&')
                    .setImageRowAndColumn(GFX_ROW_ADMIN, 9)),
    RABIES(false, 640, 64, 1, 0.67,
            new boolean[]{true, true, true, true, false, true, false, false, true, false},
            "You feel feverish.",
            "Your illness takes a toll on you.",
            GlyphBuilder.buildGlyph().setDefaults(Chroma.ORANGE, Chroma.CRIMSON, 'x')
                    .setImageRowAndColumn(GFX_ROW_ADMIN, 10)),
    //todo  - more negative status here - update FIRST_POSITIVE_STATUS!
    //todo - more positive status here
    ;

    public static final int FIRST_POSITIVE_STATUS = 3; //the index of the first positive status effect.


    //affect indices for accuracy, evasion, precision, defense, and strength are the same as for combatants
    public static final int ENERGY_GAIN = 5; // <1.0 = slower
    public static final int MOVE_ENERGY = 6; // <1.0 = faster
    public static final int ATTACK_ENERGY = 7; //<1.0 = faster
    public static final int DAMAGE = 8;
    public static final int HEALING = 9;

    public final boolean COUNTER; //describes whether this status expires by removing counters(true), or checking engine time(false)
    public final int DURATION_LIMIT; //describes the maximum duration of this effect in terms of its expiration principle.
    public final int DURATION_VARIANCE; //describes the variance of the duration of this effect in terms of its expiration principle.
    public final int FLAT; //describes the flat power of this status effect, in terms of whatever it's meant to apply.
    public final double SCALE; //describes the scaling power of this status effect, in terms of whatever it's meant to apply.
    public final boolean[] AFFECTS; //describes whether this status affects certain values.
    public final String APPLY_MESSAGE; //describes what message the player receives when this status is applied to them.
    public final String CHECK_MESSAGE; //describes what message the player receives when this status is checked successfully on them.
    public final GlyphBuilder GLYPH_BUILDER; //describe the glyph we use to display this status


    StatusType(boolean ctr, int lim, int var, int flt, double scl, boolean[] aff, String ams, String cms, GlyphBuilder gly) {
        COUNTER = ctr;
        DURATION_LIMIT = lim;
        DURATION_VARIANCE = var;
        FLAT = flt;
        SCALE = scl;
        AFFECTS = aff;
        APPLY_MESSAGE = ams;
        CHECK_MESSAGE = cms;
        GLYPH_BUILDER = gly;
    }
    public boolean isPositive() {
        return this.ordinal() >= FIRST_POSITIVE_STATUS;
    }
    public boolean affectsEngineInterval() {
        return AFFECTS[DAMAGE] || AFFECTS[HEALING];
    }

    public Glyph GLYPH() {
        return GLYPH_BUILDER.build(DualityMode.TILE);
    }
}
