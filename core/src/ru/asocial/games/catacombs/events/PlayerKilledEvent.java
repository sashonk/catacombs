package ru.asocial.games.catacombs.events;

import ru.asocial.games.catacombs.Entity;

public class PlayerKilledEvent extends EntityEvent {

    private Entity victim;
    public PlayerKilledEvent(Entity victim) {
        this.victim = victim;
    }

    public Entity getVictim() {
        return victim;
    }
}
