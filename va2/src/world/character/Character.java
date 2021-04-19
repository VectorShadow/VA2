package world.character;

/**
 * Character provides higher level attributes associated with an Actor.
 * It is implemented by NonPlayerCharacter for NPCs, where it provides a static template defining a generic NPC type.
 * It is implemented by PlayerCharacter for the Player, where it provides a dynamic reference to the Player's current
 * attributes and equipment.
 */
public interface Character {
    int getMaximumHealth();
    int getMaximumSanity();
    /*
    todo:
    Templates should carry the following data:
Name
Description
Glyph
Max Health
Max Sanity
A continuum of Attack Types
	Minimum Damage
	Maximum Damage
	Range
	Energy Cost
	Array of Damage Types
Armor Coverage
Armor Thickness
Energy gain per turn
Experience awarded
Drop table
A list of AI flags(these should include awareness level, detection range, and behavior modifiers - this may also offer special abilities to use instead of basic attacks)
A list of attribute flags(these should specify properties like resistances, damage reduction, special innate properties, etc.)
     */
}
