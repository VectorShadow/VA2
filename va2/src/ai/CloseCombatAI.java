package ai;

import engine.action.Action;
import engine.action.AdjacentAttackAction;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import main.Session;
import util.Coordinate;
import util.Direction;
import world.actor.Actor;
import world.dungeon.floor.FloorTile;
import world.terrain.TerrainTemplate;

public abstract class CloseCombatAI extends AI implements SecondaryAI {

    @Override
    public Action decide(Actor a) {
        //todo - find a hostile target, for now assume player
        Coordinate ca = a.getLocation();
        Coordinate cp = Session.getPlayer().getActor().getLocation();
        double distance = ca.distanceTo(cp);
        if (distance < 7) { //hack - todo: get an actual detection distance, check LoS
            Direction bestDirection = Direction.SELF;
            double bestDistance = distance;
            Coordinate targetCoordinate = ca;
            FloorTile targetTile;
            for (Direction d : Direction.values()) {
                targetCoordinate = d.shift(ca);
                if (targetCoordinate.equals(cp))
                    return new AdjacentAttackAction(
                            d,
                            a.getAttackEnergyMultiplier(),
                            a.getCombatant().selectMeleeWeapon()
                    );
                distance = targetCoordinate.distanceTo(cp);
                targetTile = Session.getCurrentFloor().tileAt(targetCoordinate.getRow(), targetCoordinate.getColumn());
                if (distance < bestDistance &&
                        ((TerrainTemplate)targetTile.getTerrain().getTemplate()).permitsMovement() &&
                        targetTile.getActor() == null) {
                    bestDirection = d;
                    bestDistance = distance;
                }
            }
            if (bestDirection == Direction.SELF)
                return new PauseAction();
            return new AdjacentMovementAction(
              bestDirection,
              a.getMoveEnergyMultiplier()
            );
        } else return getSecondaryAI().decide(a);
    }
}
