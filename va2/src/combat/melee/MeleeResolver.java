package combat.melee;

import combat.CombatResolver;
import engine.action.AttackAction;
import io.out.message.MessageType;
import main.Session;
import world.actor.Actor;

/**
 * CombatResolver for Melee combat interactions.
 */
public class MeleeResolver extends CombatResolver {
    public static void resolve(Actor attacker, AttackAction attackAction, Actor defender) {
        if (attacker == null || attackAction == null || defender == null)
            throw new IllegalStateException("MeleeResolver.resolve() with null arg");
        Session.getMessageCenter().sendMessage("Melee attack test", MessageType.ERROR);
    }
}
