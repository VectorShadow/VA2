package world.terrain;

import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;

/**
 * Contains information required to describe and represent a generic type of terrain.
 */
public class TerrainTemplate extends WorldObjectTemplate {
    private final boolean PERMITS_LIGHT;
    private final boolean PERMITS_MOVEMENT;
    private final boolean MESSAGE_ON_MOVE;

    public TerrainTemplate(
            String d,
            String n,
            BalancedGlyphTemplate bgt,
            boolean r,
            boolean perLight,
            boolean perMove,
            boolean msgMove
    ) {
        super(d, n, bgt, r);
        PERMITS_LIGHT = perLight;
        PERMITS_MOVEMENT = perMove;
        MESSAGE_ON_MOVE = msgMove;
    }
    private TerrainTemplate(TerrainTemplate tt) {
        this(
          tt.DESCRIPTION,
          tt.NAME,
          tt.BALANCED_GLYPH_TEMPLATE.clone(),
          tt.reflectsLight(),
          tt.PERMITS_LIGHT,
          tt.PERMITS_MOVEMENT,
          tt.MESSAGE_ON_MOVE
        );
    }

    public boolean permitsLight() {
        return PERMITS_LIGHT;
    }

    public boolean permitsMovement() {
        return PERMITS_MOVEMENT;
    }

    public boolean isMessageOnMove() {
        return MESSAGE_ON_MOVE;
    }

    @Override
    public TerrainTemplate clone() {
        return new TerrainTemplate(this);
    }
}
