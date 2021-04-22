package combat;

import org.junit.Test;

public class CombatCalculatorTest {
    @Test()
    public void testSumNegative() {
        //todo
    }
    @Test
    public void testSumOutOfBounds() {
        //todo
    }
    @Test
    public void testSumInit() {
        Integer expected = 1;
        Integer result = CombatCalculator.sum(1);
        assert result.equals(expected);
    }
    @Test
    public void testSumExtend() {
        Integer expected = 36;
        Integer result = CombatCalculator.sum(8);
        assert result.equals(expected);
    }
    @Test
    public void testHitChanceNegative() {
        //todo
    }
    @Test
    public void testHitChanceZero() {
        Double expected = 100.0;
        Double result = CombatCalculator.baseHitChance(0.0);
        assert result.equals(expected);
    }
    @Test
    public void testHitChanceMinimum() {
        Double expected = 9.0;
        Double result = CombatCalculator.baseHitChance(13);
        assert result.equals(expected);
    }
    @Test
    public void testHitChanceOutOfRange() {
        Double expected = 0.0;
        Double result = CombatCalculator.baseHitChance(16);
        assert result.equals(expected);
    }
    @Test
    public void testPercentToDecimal() {
        Double expected = 0.5;
        Double result = CombatCalculator.percentToDecimal(50.0);
        assert result.equals(expected);
    }
}
