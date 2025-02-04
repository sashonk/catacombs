package ru.asocial.games.catacombs.events;

import com.badlogic.gdx.scenes.scene2d.Event;
import ru.asocial.games.catacombs.Entity;

public class RestartEvent extends Event {

    private Entity player;
    private boolean nextLvl;

    public RestartEvent(Entity player, boolean nextLevel) {
        this.player = player;
        this.nextLvl = nextLevel;
    }

    public Entity getPlayer() {
        return player;
    }

    public boolean isNextLvl() {
        return nextLvl;
    }
}
