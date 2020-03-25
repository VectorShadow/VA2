package main;

import io.out.VisibleCoordinate;
import util.Coordinate;
import world.actor.Actor;
import world.dungeon.floor.FloorTile;

import java.util.ArrayList;

public class TargetList {
    private ArrayList<VisibleCoordinate> playerView = new ArrayList<>();
    private ArrayList<Actor> targets = new ArrayList<>();
    private int index = -1;

    public TargetList() {
        reset();
    }
    public void reset() {
        Actor currentTarget = get();
        playerView = Session.getFloorRenderer().getPlayerView();
        targets = new ArrayList<>();
        int r;
        int c;
        FloorTile ft;
        Actor a;
        for (VisibleCoordinate vc : playerView) {
            r = vc.getRow();
            c = vc.getColumn();
            ft = Session.getCurrentFloor().tileAt(r, c);
            a = ft.getActor();
            if (a != null) targets.add(a);
        }
        Coordinate playerLocation = Session.getPlayer().getActor().getLocation();
        targets.sort(
                (Actor a1, Actor a2) ->
                        (int)(a1.getLocation().distanceTo(playerLocation) - a2.getLocation().distanceTo(playerLocation))
        );
        if (index <= 0 || !setTarget(currentTarget.getLocation())) {
            index = size() > 1 ? 1 : size() > 0 ? 0 : -1;
        }
    }

    /**
     * Clear the current target. This is used to reset the cursor to the player.
     */
    public void clearTarget() {
        index = -1;
    }

    public void cycle(boolean forward) {
        if (size() <= 0) {
            index = -1;
            return;
        }
        index += (forward ? 1 : -1);
        if (index >= size()) index = 0;
        if (index < 0) index = size() - 1;
    }

    public int size() {
        return targets.size();
    }
    public Actor get() {
        if (index < 0) return Session.getPlayer().getActor(); //no currently targeted actor
        return targets.get(index); //return the actor at the specified position
    }

    /**
     * Returns whether the specified Coordinate corresponds to the location of an Actor in the target list.
     */
    public boolean setTarget(Coordinate c) {
        for (int i = 0; i < targets.size(); ++i) {
            if (c.equals(targets.get(i).getLocation())) {
                index = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the specified Coordinate has the same row and column as some VisibleCoordinate in the playerView.
     */
    public boolean visible(Coordinate c) {
        for (VisibleCoordinate vc : playerView) {
            if (c.equals(vc)) return true; //Coordinate.equals() compares row and column
        }
        return false;
    }
}
