package combat.melee;

import combat.*;
import combat.melee.forms.Form;
import combat.melee.weapons.MeleeStyle;
import combat.melee.weapons.ResolvableMeleeWeapon;
import engine.action.ActionDefinitions;
import engine.action.CounterAttackAction;
import engine.action.MeleeAttackAction;
import io.out.message.Message;
import io.out.message.MessageType;
import main.Session;
import util.Grammar;
import world.actor.Actor;
import world.actor.ActorTemplate;
import world.dungeon.Dungeon;
import world.dungeon.floor.Floor;
import world.dungeon.theme.ActorSet;

/**
 * CombatResolver for Melee combat interactions.
 */
public class MeleeResolver extends CombatResolver {
    public static Message message;

    public static Message resolve(Actor attacker, MeleeAttackAction meleeAttackAction, Actor defender) {
        if (attacker == null || meleeAttackAction == null || defender == null)
            throw new IllegalStateException("MeleeResolver.resolve() with null arg");
        /**
         * Initial setup:
         */
        boolean isAttackerPlayer = attacker == Session.getPlayer().getActor();
        boolean isDefenderPlayer = defender == Session.getPlayer().getActor();
        String attackerName = attacker.getTemplate().getName();
        String defenderName = defender.getTemplate().getName();
        Combatant attackerCombatant = attacker.getCombatant();
        Combatant defenderCombatant = defender.getCombatant();
        Form attackerForm = attackerCombatant.getMeleeForm();
        Form defenderForm = defenderCombatant.getMeleeForm();
        AttackTactic attackTactic =
                (meleeAttackAction instanceof CounterAttackAction)
                ? ((CounterAttackAction)meleeAttackAction).getAttackTactic() //force the counter tactic
                : isAttackerPlayer
                ? attackerForm.selectAttackTactic() //todo - use the player's set tactic
                : attackerForm.selectAttackTactic(); //select a random available tactic
        DefenseTactic defenseTactic =
                (meleeAttackAction instanceof CounterAttackAction && //force the counter tactic
                        ((CounterAttackAction) meleeAttackAction).getDefenseTactic() != null)
                ? ((CounterAttackAction)meleeAttackAction).getDefenseTactic()
                : isDefenderPlayer
                ? defenderForm.selectDefenseTactic() //todo - use the player's set tactic
                : defenderForm.selectDefenseTactic(); //select a random available tactic
        ResolvableMeleeWeapon attackerResolvableMeleeWeapon = meleeAttackAction.getResolvableMeleeWeapon();
        WeaponDamage weaponDamage = attackerResolvableMeleeWeapon.resolveWeaponDamage(attackTactic == AttackTactic.BLOW);
        //counterattacks preserve any existing message - otherwise we need to reset it
        if (!(meleeAttackAction instanceof CounterAttackAction)){
            message = (isAttackerPlayer || isDefenderPlayer)
                    ? new Message(
                            MessageType.INFO,
                            Grammar.configure(
                                isAttackerPlayer,
                                "You",
                                "The " + attackerName,
                                attackTactic == AttackTactic.STRIKE
                                        ? weaponDamage.describe()
                                        : attackTactic.message,
                                "the " + defenderName + ".",
                                "you."
                            ) + "  " +
                                    Grammar.configure(
                                            isDefenderPlayer,
                                            "You",
                                            "The " + defenderName,
                                            defenseTactic.message,
                                            "the attack.",
                                            "the attack."
                                    )
            ) : null; //if the player is not involved, message remains null
        }
        if (defenseTactic == DefenseTactic.IGNORE)
            defenderCombatant.setIgnoreBonus();
        /**
         * Special Interactions:
         */
        double attackerAccuracyMultiplier = attackTactic.baseAccuracyMultiplier;
        double attackerPrecisionMultiplier = attackTactic.basePrecisionMultiplier;
        double attackerDamageMultiplier = attackTactic.baseDamageMultiplier;
        double defenderCounterChance = defenseTactic.baseCounterChance;
        double defenderEvasionMultiplier = defenseTactic.baseEvasionMultiplier;
        double defenderDeflectionMultiplier = defenseTactic.baseDeflectionMultiplier;
        double defenderDefenseMultiplier = defenseTactic.baseDefenseMultiplier;
        double defenderDamageReductionMultiplier = defenseTactic.baseDamageMultiplier;
        boolean attackerHasIgnoreBonus = attackerCombatant.hasIgnoreBonus(); //consume and apply attacker's ignore bonus
        boolean cancelAttack = false;
        boolean cancelAttackUnlessCountered = false;
        boolean preventEvasionDeflection = false;
        boolean boostCounterStrike = false;
        boolean negateCounterStrike = false;
        switch (attackTactic) {
            case PROBE:
                switch (defenseTactic) {
                    case EVADE: case DEFLECT: case BRACE:
                        defenderCounterChance = 0.0; //Probe may not be countered except by Riposte
                        break;
                    case RIPOSTE:
                        defenderCounterChance *= 0.25; //Probe is much less likely to be countered by Riposte
                        defenderDeflectionMultiplier += 0.1; //deflecting a Probe becomes slightly easier
                        break;
                    case IGNORE:
                        attackerAccuracyMultiplier *= 1.333; //Probe becomes more accurate against Ignore
                        attackerPrecisionMultiplier = 0.25; //Probe gains critical chance against Ignore
                        attackerDamageMultiplier *= 2.0; //Probe does double damage against Ignore
                        break;
                }
                break;
            case FEINT:
                switch (defenseTactic) {
                    case EVADE: case DEFLECT: case BRACE:
                        attackerPrecisionMultiplier *= 1.25; //Feint becomes more precise
                        attackerDamageMultiplier *= 1.25; //Feint deals more damage
                        cancelAttackUnlessCountered = true; //Feint does not perform an attack unless the defender counters
                        preventEvasionDeflection = true; //defender may not evade or deflect against a countered Feint
                        break;
                    case RIPOSTE:
                        attackerPrecisionMultiplier *= 1.25; //Feint becomes more precise
                        attackerDamageMultiplier *= 1.25; //Feint deals more damage
                        defenderEvasionMultiplier *= 0.5; //defender's chance to evade is halved
                        defenderDeflectionMultiplier *= 0.5; //defender's chance to deflect is halved
                        defenderDefenseMultiplier *= 0.5; //defender chance to mitigate critical hits is halved
                        break;
                    case IGNORE: //Feint is downgraded to Probe effectiveness against Ignore
                        attackerAccuracyMultiplier = AttackTactic.PROBE.baseAccuracyMultiplier;
                        attackerPrecisionMultiplier = AttackTactic.PROBE.basePrecisionMultiplier;
                        attackerDamageMultiplier = AttackTactic.PROBE.baseDamageMultiplier;
                        if (isAttackerPlayer)
                            updateMessage("Your ignored feint is less effective.", MessageType.WARNING);
                        break;
                }
                break;
            case STRIKE:
                switch (defenseTactic) {
                    case BRACE:
                        attackerPrecisionMultiplier *= 0.9; //Strike has reduced critical chance against Brace
                        attackerDamageMultiplier *= 0.5; //Strike has halved damage against Brace
                        break;
                    case IGNORE:
                        attackerPrecisionMultiplier *= 1.25; //Strike has increased critical chance against Ignore
                        break;
                }
                break;
            case BLOW:
                boostCounterStrike = true; //all successful counters against Blows are themselves upgraded to Blow
                switch (defenseTactic) {
                    case EVADE: case DEFLECT:
                        attackerAccuracyMultiplier *= 0.667; //Blow is less accurate against Evade and Deflect
                        break;
                    case BRACE:
                        defenderEvasionMultiplier *= 2.5; //Brace is much more likely to Evade a Blow
                        defenderDeflectionMultiplier *= 2.5; //Brace is much more likely to Deflect a Blow
                        defenderDefenseMultiplier = 1.0; //Brace loses its additional Critical protection
                        break;
                    case IGNORE:
                        defenderDefenseMultiplier = 0.0; //Ignore loses all Critical protection
                        break;
                }
                break;
            case ANTICIPATE:
                negateCounterStrike = true;
                switch (defenseTactic) {
                    case EVADE:
                        defenderEvasionMultiplier *= 0.5; //Evasion is half as effective
                        break;
                    case DEFLECT:
                        defenderDeflectionMultiplier *= 0.5; //Deflection is half as effective
                        break;
                    case BRACE:
                        defenderEvasionMultiplier = 1.0; //Brace gets full Evasion
                        defenderDeflectionMultiplier = 1.0; //Brace gets full Deflection
                        break;
                    case IGNORE:
                        cancelAttack = true; //Make no attack against Ignore
                        if (isAttackerPlayer){
                            updateMessage(
                                    "You failed to anticipate the " + defenderName + "'s disdain.",
                                    MessageType.WARNING
                            );
                        }
                        break;
                }
                break;
        }
        /**
         * Calculate effective values:
         */
        int effectiveAttackerAccuracy = (int)(attackerAccuracyMultiplier *
                ((double)attacker.getAdjustedAccuracy() + (double) attackerResolvableMeleeWeapon.adjustAccuracy()));
        int effectiveAttackerPrecision = (int)(attackerPrecisionMultiplier *
                ((double)attacker.getAdjustedPrecision() + (double) attackerResolvableMeleeWeapon.adjustPrecision()));
        int effectiveAttackerStrength = attacker.getAdjustedStrength() + attackerResolvableMeleeWeapon.adjustStrength();
        ResolvableMeleeWeapon defenderResolvableMeleeWeapon = defenderCombatant.selectMeleeWeapon();
        MeleeStyle defenderStyle = defenderResolvableMeleeWeapon.getMeleeStyle();
        int effectiveDefenderEvasion = (int)(defenderEvasionMultiplier * (double)defender.getAdjustedEvasion());
        int effectiveDefenderDeflection = (int)(defenderDeflectionMultiplier *
                (double)(defenderStyle == MeleeStyle.SHIELD
                ? defender.getAdjustedEvasion() : //evasion for blocking todo - bonus for bigger shields?
                defenderStyle == MeleeStyle.DUAL_WEAPON
                        ? defender.getAdjustedAccuracy() //accuracy for parrying
                        : (int)(0.5 * (double)defender.getAdjustedAccuracy()))); //halved if not shield or dual
        int effectiveDefenderDefense = (int)(defenderDefenseMultiplier * (double)defender.getAdjustedDefense());
        if (attackerHasIgnoreBonus) { //apply the bonus from ignoring an incoming attack
            effectiveAttackerAccuracy = (int)(1.25 * (double)effectiveAttackerAccuracy);
            effectiveAttackerPrecision = (int)(1.25 * (double)effectiveAttackerPrecision);
            attackerDamageMultiplier *= 1.25;
        }
        if (meleeAttackAction instanceof CounterAttackAction) {
            effectiveDefenderEvasion *= 0.667; //significantly harder to evade a counter
            effectiveDefenderDeflection *= 0.85; //slightly harder to deflect a counter
            effectiveDefenderDefense *= 0.85; //slightly harder to mitigate critical hits from a counter
            defenderCounterChance *= 0.25; //almost impossible to counter a countery
        }
        /**
         * Defender reacts to the attack if he has enough energy:
         */
        int reactionEnergy = ActionDefinitions.REACT_TO_ATTACK_ENERGY_COST;
        if (defender.hasEnoughEnergy(reactionEnergy)) {
            defender.consumeEnergy(reactionEnergy);
            /**
             * Defender attempts to counter the attack:
             */
            if (!(defenseTactic == DefenseTactic.IGNORE) && // if defender is not ignoring attacks
                    defender.hasEnoughEnergy(reactionEnergy) && //and the defender also has enough energy to counter
                    Session.getRNG().nextDouble() < defenderCounterChance //and the counter roll succeeds
            ) {
                defender.consumeEnergy(reactionEnergy); //expend additional reaction energy
                if (!(attackTactic == AttackTactic.FEINT)) {
                    if (!attackerHasIgnoreBonus)
                        cancelAttack = true; //cancel the attacker's attack, unless he has ignore bonus
                    if (isAttackerPlayer || isDefenderPlayer) {
                        updateMessage(
                                Grammar.configure(
                                        isDefenderPlayer,
                                        "You",
                                        "The " + defenderName,
                                        "counter&",
                                        "the " + attackerName + "'s attack.",
                                        "your attack."
                                ),
                                isAttackerPlayer ? MessageType.ERROR : MessageType.SUCCESS
                        );
                    }
                    if (!negateCounterStrike){
                        MeleeResolver.resolve( //todo - pass additional constructor arguments as necessary
                                defender,
                                new CounterAttackAction(
                                        boostCounterStrike ? AttackTactic.BLOW : AttackTactic.STRIKE,
                                        (defenseTactic == DefenseTactic.EVADE || //if defender countered while evading
                                                defenseTactic == DefenseTactic.DEFLECT) //or while deflecting
                                                ? defenseTactic //the attacker must defend the same way
                                                : attackerHasIgnoreBonus //if attacker has ignore bonus, his attack is not
                                                ? DefenseTactic.IGNORE //negated, but he may not defend against the counter
                                                : null, //otherwise, the attacker has freedom of defensive tactics
                                        defenderResolvableMeleeWeapon
                                ),
                                attacker
                        );
                    } else if (isAttackerPlayer || isDefenderPlayer) {
                        updateMessage(
                                Grammar.configure(
                                        isAttackerPlayer,
                                        "You",
                                        "The " + attackerName,
                                        "anticipate&",
                                        "the " + defenderName + "'s counterattack.",
                                        "your counterattack."
                                ),
                                isAttackerPlayer ? MessageType.SUCCESS : MessageType.WARNING
                        );
                    }
                } else if (isAttackerPlayer || isDefenderPlayer) {
                        updateMessage(
                                Grammar.configure(
                                        isDefenderPlayer,
                                        "You",
                                        "The " + defenderName,
                                        "fall& for",
                                        "the " + attackerName + "'s feint.",
                                        "your feint."
                                ),
                                isAttackerPlayer ? MessageType.ERROR : MessageType.SUCCESS
                        );
                }
            } else if (cancelAttackUnlessCountered) {
                cancelAttack = true; //cancel the attacker's attack if there was no counter
                if (isAttackerPlayer || isDefenderPlayer) {
                    updateMessage(
                            Grammar.configure(
                                    isAttackerPlayer,
                                    "Your",
                                    "The " + attackerName + "'s",
                                    "feint",
                                    "fails to provoke a counterattack.",
                                    "fails to provoke a counterattack."
                            ),
                            MessageType.INFO
                    );
                }
            }
            if (cancelAttack) //if the attacker's attack has been cancelled for any reason, we are done.
                return message;
            /**
             * Attempt to evade and/or deflect:
             */
            if (!preventEvasionDeflection) {
                if (!Functions.oppose(effectiveAttackerAccuracy, effectiveDefenderEvasion)) {
                    if (isAttackerPlayer || isDefenderPlayer) {
                        updateMessage(
                                Grammar.configure(
                                        isDefenderPlayer,
                                        "You",
                                        "The " + defenderName,
                                        "evade&",
                                        "the " + attackerName + "'s attack.",
                                        "your attack."
                                ),
                                isAttackerPlayer ? MessageType.WARNING : MessageType.SUCCESS
                        );
                    }
                    return message;
                } else if (effectiveDefenderEvasion > 0 && (isAttackerPlayer || isDefenderPlayer)) {
                    updateMessage(
                            Grammar.configure(
                                    isDefenderPlayer,
                                    "You",
                                    "The " + defenderName,
                                    "fail& to evade",
                                    "the " + attackerName + "'s attack.",
                                    "your attack."
                            ),
                            isDefenderPlayer ? MessageType.WARNING : MessageType.SUCCESS
                    );
                }
                if (!Functions.oppose(effectiveAttackerAccuracy, effectiveDefenderDeflection)) {
                    if (isAttackerPlayer || isDefenderPlayer) {
                        updateMessage(
                                Grammar.configure(
                                        isDefenderPlayer,
                                        "You",
                                        "The " + defenderName,
                                        "deflect&",
                                        "the " + attackerName + "'s attack.",
                                        "your attack."
                                ),
                                isAttackerPlayer ? MessageType.WARNING : MessageType.SUCCESS
                        );
                    }
                    //todo - damage weapons/shields
                    return message;
                } else if (effectiveDefenderDeflection > 0 && (isAttackerPlayer || isDefenderPlayer)) {
                    updateMessage(
                            Grammar.configure(
                                    isDefenderPlayer,
                                    "You",
                                    "The " + defenderName,
                                    "fail& to deflect",
                                    "the " + attackerName + "'s attack.",
                                    "your attack."
                            ),
                            isDefenderPlayer ? MessageType.WARNING : MessageType.SUCCESS
                    );
                }
            }
        }
        if (cancelAttack) //if the attacker's attack has been cancelled for any reason, we are done.
            return message;
        /**
         * Roll for and adjust damage.
         */
        int attackDamage =
                (int)(attackerDamageMultiplier *
                        defenderDamageReductionMultiplier *
                        weaponDamage.modify() *
                        (double)(attackerResolvableMeleeWeapon.rollRawDamage(effectiveAttackerStrength)));
        //hack - tell me how much damage I'm doing!
        if (isAttackerPlayer || isDefenderPlayer) {
            updateMessage(
                    Grammar.configure(
                            isAttackerPlayer,
                            "You hit the " + defenderName + " for",
                            "The " + attackerName + " hits you for",
                            "" + attackDamage,
                            "damage.",
                            "damage."
                    ),
                    isAttackerPlayer ? MessageType.SUCCESS : MessageType.ERROR
            );
        }
        //todo - critical damage
        //todo - armor
        if (!defenderCombatant.adjustHealth(-attackDamage)) {
            Session.killActor(defender);
            if (isAttackerPlayer) {
                Dungeon d = Session.getCurrentDungeon();
                Floor f = Session.getCurrentFloor();
                ActorSet as = f.THEME.getActorSet();
                if (as.isFloorBoss(defender)) {
                    updateMessage("You have defeated " + defenderName + ", the guardian of this area.", MessageType.SUCCESS);
                    f.killFloorBoss();
                } else if (as.getDungeonBossSet().length > 0 && as.getDungeonBossSet()[0] == defender.getTemplate()) {
                    updateMessage("You have defeated " + defenderName + ", the final guardian of this dungeon.", MessageType.SUCCESS);
                    d.killDungeonBoss();
                } else
                    updateMessage("You have slain the " + defenderName + ".", MessageType.INFO);
                d.addReward(((ActorTemplate)defender.getTemplate()).getReward());
            }
        }
        return message;
    }
    private static void updateMessage(String addition, MessageType type) {
        message.append(new Message(type, addition));
        message.changeType(type);
    }
}
