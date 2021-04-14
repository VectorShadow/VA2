package main.modes.menu.estate;

import io.out.GUIManager;
import main.Session;
import main.modes.menu.MenuDefinitions;
import main.modes.menu.MenuMode;
import resources.glyph.GlyphString;

import java.awt.*;

public class ScrollableItemSelectionMode extends MenuMode {

    private final ItemSelectionExecutor EXECUTOR;

    public ScrollableItemSelectionMode(ItemSelectionExecutor executor) {
        EXECUTOR = executor;
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        EXECUTOR.select(index);
        Session.getModeManager().revert();
    }

    @Override
    public void to() {
        setMenu(MenuDefinitions.generateInventoryMenu(EXECUTOR.getInventory()));
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from(){
        Session.getModeManager().revert();
        int index = EXECUTOR.getSelectedIndex();
        if (index >= 0 && index < EXECUTOR.INVENTORY.size()) {
            EXECUTOR.handleSelection();
        }
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        Point lastLineEnd = gm.printCenteredLine(0.2, "Select an Item:");
        int listLineRow = lastLineEnd.y + 2;
        for (int i = menu.getSelectedOptionIndex(); i < menu.size() - 1; ++i) {
            GlyphString coloredName = EXECUTOR.getInventory().get(i).peekItem().getQualityColoredName(i);
            if (i == menu.getSelectedOptionIndex())
                coloredName = new GlyphString(
                        new GlyphString("> ",
                                Session.getColorScheme().getBackground(),
                                Session.getColorScheme().getForeground()),
                        coloredName
                );
            gm.printGlyphString(listLineRow++, 4, coloredName);
        }
        gm.printGlyphString(
                listLineRow,
                4,
                new GlyphString(
                        "Exit",
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
    }
}
