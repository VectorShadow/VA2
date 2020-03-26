package world.item;

import combat.ContactInteractive;
import world.item.loadout.EquipmentSlot;
import world.item.material.Material;

/**
 * This class specifies all items which may be used in a contact interaction.
 * This default level assumes that neither the combatant using this item, nor the item itself,
 * will be harmed by such an interaction.
 */
public class ContactInteractiveItem extends EquipableItem implements ContactInteractive {

    protected final boolean INNATE;

    public ContactInteractiveItem(ItemTemplate it, boolean doesDegrade, EquipmentSlot es, boolean innate) {
        super(it, doesDegrade, es);
        INNATE = innate;
    }
    ContactInteractiveItem(ContactInteractiveItem cii) {
        this(((ItemTemplate)cii.getTemplate()), cii.DOES_DEGRADE, cii.getEquipmentSlot(), cii.INNATE);
    }


    /**
     * Innate items damage their actors, not themselves. Since items must have far more durability than an actor
     * needs to have health, reduce the damage dealt to the actor to prevent inordinate combat damage from
     * these interactions.
     */
    @Override
    public double getSelfDamageMultiplier() {
        return INNATE ? 0.125 : 1.0;
    }

    @Override
    public boolean doesDamageSelf() {
        return !INNATE;
    }

    /**
     * Returns whether this item has durability remaining.
     * Innate items do not take damage themselves, so always return true. Other items should call their own
     * damage method and return the result of that.
     */
    @Override
    public boolean damageSelf(int amount) {
        return INNATE || damage(amount);
    }

    @Override
    public ContactInteractiveItem clone() {
        return new ContactInteractiveItem(this);
    }
}
