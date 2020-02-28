package engine.action;

/**
 * An action which serves to pass a small amount of time.
 */
public class PauseAction extends Action {
    public PauseAction() {
        super(ActionDefinitions.PAUSE_ACTION_ENERGY_COST);
    }
}
