package status;

import main.Session;

public class StatusEffect {
    private final StatusType TYPE;
    private long expiration;

    public StatusEffect(StatusType st, long exp) {
        TYPE = st;
        expiration = exp;
    }

    public void updateExpiration(long exp) {
        if (TYPE.COUNTER)
                expiration += exp;
                expiration = Math.max(expiration, Session.getEngine().getGameTurn() + exp);
    }

    public StatusType getType() {
        return TYPE;
    }

    public long getExpiration() {
        return expiration;
    }

    /**
     * Check this status effect and consume a counter if applicable (for game update checks)
     */
    public boolean consume() {
        if (TYPE.COUNTER) --expiration;
        return inEffect();
    }

    /**
     * Check this status effect but do not consume a counter(for display checks)]
     */
    public boolean inEffect() {
        return TYPE.COUNTER ? expiration > 0 : expiration > Session.getEngine().getGameTurn();
    }
}
