package world.character;

public class PlayerCharacter implements Character {

    private int maximumHealth = 100;
    private int maximumSanity = 100;

    //todo - tons here. Should max fields be final, with getters referencing talent tree?

    @Override
    public int getMaximumHealth() {
        return maximumHealth;
    }

    @Override
    public int getMaximumSanity() {
        return maximumSanity;
    }
}
