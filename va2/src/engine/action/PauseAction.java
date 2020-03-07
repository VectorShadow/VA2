package engine.action;

/**
 * An action which serves to pass a small amount of time.
 */
public class PauseAction extends Action {
    public PauseAction() {
        super(1.0);
    }

    @Override
    protected int getDefaultEnergyCost() {
        return ActionDefinitions.PAUSE_ACTION_ENERGY_COST;
    }
}
