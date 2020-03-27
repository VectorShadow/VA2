package world.item;

import combat.ContactInteractive;
import world.item.loadout.EquipmentSlot;
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
        this(((ItemTemplate)cii.getTEMPLATE()), cii.DOES_DEGRADE, cii.getEquipmentSlot(), cii.INNATE);
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

    /**
     * Innate items damage their actors, not themselves. If an innate item ought not damage its actor(for example,
     * a projected attack like spider silk, where the projection is not actually a part of the actor's body, even if it
     * is an innate item rather than an equipped one), instead set innate to false, as well as does degrade to false.
     * This way it will neither damage itself nor its actor.
     */
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
