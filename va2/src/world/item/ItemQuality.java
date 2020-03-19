package world.item;

import main.Session;
import resources.chroma.Chroma;
import resources.glyph.BalancedGlyphTemplate;
import util.ArrayListBuilder;

import java.awt.*;
import java.util.ArrayList;

public enum ItemQuality {
    INNATE_ITEM,
    MUNDANE_PRODUCT,
    GOOD_PRODUCT,
    SUPERIOR_PRODUCT,
    EXCEPTIONAL_PRODUCT,
    MASTERWORK_PRODUCT,
    COMMON_RELIC,
    SCARCE_RELIC,
    RARE_RELIC,
    EPIC_RELIC,
    LEGENDARY_RELIC,
    MYTHIC_RELIC,
    FABLED_RELIC,
    BASIC_REAGENT,
    STABLE_REAGENT,
    VOLATILE_REAGENT,
    POTENT_REAGENT,
    CATALYST_REAGENT,
    SIMPLE_RESOURCE,
    REFINED_RESOURCE,
    EXOTIC_RESOURCE,
    PRECIOUS_RESOURCE;

    public BalancedGlyphTemplate getBalancedGlyphTemplate() {
        ArrayList<Color> foregrounds = new ArrayList<>();
        switch (this) {
            case INNATE_ITEM: foregrounds.add(Chroma.FLESH);
            case MUNDANE_PRODUCT: foregrounds.add(Chroma.METALLIC_IRON); break;
            case GOOD_PRODUCT: foregrounds.add(Chroma.METALLIC_BRONZE); break;
            case SUPERIOR_PRODUCT: foregrounds.add(Chroma.METALLIC_SILVER); break;
            case EXCEPTIONAL_PRODUCT: foregrounds.add(Chroma.METALLIC_GOLD);break;
            case MASTERWORK_PRODUCT:
                foregrounds.add(Chroma.METALLIC_BRONZE);
                foregrounds.add(Chroma.METALLIC_SILVER);
                foregrounds.add(Chroma.METALLIC_GOLD);
                foregrounds.add(Chroma.METALLIC_PLATINUM);
                break;
            case COMMON_RELIC: foregrounds.add(Chroma.GREY); break;
            case SCARCE_RELIC: foregrounds.add(Chroma.GREEN); break;
            case RARE_RELIC: foregrounds.add(Chroma.BLUE); break;
            case EPIC_RELIC: foregrounds.add(Chroma.RED); break;
            case LEGENDARY_RELIC: foregrounds.add(Chroma.WHITE); break;
            case MYTHIC_RELIC:
                foregrounds.add(Chroma.GREY);
                foregrounds.add(Chroma.YELLOW);
                foregrounds.add(Chroma.MAGENTA);
                foregrounds.add(Chroma.CYAN);
                break;
            case FABLED_RELIC:
                foregrounds.add(Chroma.GREEN);
                foregrounds.add(Chroma.BLUE);
                foregrounds.add(Chroma.RED);
                foregrounds.add(Chroma.YELLOW);
                foregrounds.add(Chroma.MAGENTA);
                foregrounds.add(Chroma.CYAN);
                foregrounds.add(Chroma.WHITE);
                break;
            case BASIC_REAGENT: foregrounds.add(Chroma.AQUA); break;
            case STABLE_REAGENT: foregrounds.add(Chroma.TURQUOISE); break;
            case VOLATILE_REAGENT:
                foregrounds.add(Chroma.ORANGE);
                foregrounds.add(Chroma.CRIMSON);
                break;
            case POTENT_REAGENT:
                foregrounds.add(Chroma.SLIME);
                foregrounds.add(Chroma.VIOLET);
                break;
            case CATALYST_REAGENT:
                foregrounds.add(Chroma.AQUA);
                foregrounds.add(Chroma.SLIME);
                foregrounds.add(Chroma.TURQUOISE);
                foregrounds.add(Chroma.VIOLET);
                foregrounds.add(Chroma.ORANGE);
                foregrounds.add(Chroma.CRIMSON);
                break;
            case SIMPLE_RESOURCE: foregrounds.add(Chroma.COPPER); break;
            case REFINED_RESOURCE: foregrounds.add(Chroma.COBALT); break;
            case EXOTIC_RESOURCE: foregrounds.add(Chroma.RUST); break;
            case PRECIOUS_RESOURCE:
                foregrounds.add(Chroma.COPPER);
                foregrounds.add(Chroma.COBALT);
                foregrounds.add(Chroma.RUST);
                break;
                default:
                    throw new IllegalStateException("Unhandled Quality: " + this);
        }
        return new BalancedGlyphTemplate(
                ArrayListBuilder.initialize().addElement(' ').build(),
                ArrayListBuilder.initialize().addElement(Session.getColorScheme().getBackground()).build(),
                foregrounds
        );
    }
}
