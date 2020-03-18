package world.item.loadout;

import main.extensible.Saveable;
import resources.glyph.GlyphString;

public abstract class LoadoutModule extends Saveable {
    public GlyphString display() {
        return null; //todo - make abstract and delegate to subclasses - display something for the GUI
    }
}
