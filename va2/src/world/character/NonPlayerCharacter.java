package world.character;

public class NonPlayerCharacter implements Character{

    //todo - final fields, String-based template constructor?

    @Override
    public int getMaximumHealth() {
        return 0;
    }

    @Override
    public int getMaximumSanity() {
        return 0;
    }
}
