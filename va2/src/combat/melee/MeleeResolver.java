package combat.melee;

import combat.CombatResolver;
import combat.Combatant;
import combat.melee.forms.Form;
import combat.melee.weapons.MeleeStyle;
import combat.melee.weapons.MeleeWeapon;
import engine.action.MeleeAttackAction;
import io.out.message.Message;
import main.Session;
import world.actor.Actor;

/**
 * CombatResolver for Melee combat interactions.
 */
public class MeleeResolver extends CombatResolver {
    public static Message resolve(Actor attacker, MeleeAttackAction meleeAttackAction, Actor defender) {
        if (attacker == null || meleeAttackAction == null || defender == null)
            throw new IllegalStateException("MeleeResolver.resolve() with null arg");
        /**
         * Initial setup:
         */
        boolean isAttackerPlayer = attacker == Session.getPlayer().getActor();
        boolean isDefenderPlayer = defender == Session.getPlayer().getActor();
        Combatant attackerCombatant = attacker.getCombatant();
        Combatant defenderCombatant = defender.getCombatant();
        Form attackerForm = attackerCombatant.getMeleeForm();
        Form defenderForm = defenderCombatant.getMeleeForm();
        AttackTactic attackTactic = isAttackerPlayer
                ? attackerForm.selectAttackTactic() //todo - use the player's set tactic
                : attackerForm.selectAttackTactic();
        DefenseTactic defenseTactic = isDefenderPlayer
                ? defenderForm.selectDefenseTactic() //todo - use the player's set tactic
                : defenderForm.selectDefenseTactic();
        MeleeWeapon attackerMeleeWeapon = meleeAttackAction.getMeleeWeapon();
        int effectiveAttackerAccuracy = attacker.getAdjustedAccuracy() + attackerMeleeWeapon.adjustAccuracy();
        int effectiveAttackerPrecision = attacker.getAdjustedPrecision() + attackerMeleeWeapon.adjustPrecision();
        int effectiveAttackerStrength = attacker.getAdjustedStrength() + attackerMeleeWeapon.adjustStrength();
        MeleeWeapon defenderMeleeWeapon = defenderCombatant.selectMeleeWeapon();
        MeleeStyle defenderStyle = defenderMeleeWeapon.getMeleeStyle();
        int effectiveDefenderEvasion = defender.getAdjustedEvasion();
        int effectiveDefenderDeflection = defenderStyle == MeleeStyle.SHIELD
                ? defender.getAdjustedEvasion() : //evasion for blocking
                defenderStyle == MeleeStyle.DUAL_WEAPON
                        ? defender.getAdjustedAccuracy() //accuracy for parrying
                        : (int)(0.5 * (double)defender.getAdjustedAccuracy()); //halved if not shield or dual
        int effectiveDefenderDefense = defender.getAdjustedDefense();
        double effectiveDefenderCounter = defenseTactic.counterChance;
        double effectiveDefenderDamageReduction = defenseTactic.damageAdjustment;
        /**
         * Special Interactions:
         */
        switch (attackTactic) { //todo - all multiplications of effective stats are done here. Use default case!
            case PROBE:
                switch (defenseTactic) {
                    case EVADE:

                        break;

                }
                break;
        }
        //todo:
        return null;
    }
}
