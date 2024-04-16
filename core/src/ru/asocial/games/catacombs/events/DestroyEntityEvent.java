package ru.asocial.games.catacombs.events;

import ru.asocial.games.catacombs.Entity;

public class DestroyEntityEvent extends EntityEvent {

    private Entity relatedEntity;
    private boolean squized;

    public void setRelatedEntity(Entity relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    public Entity getRelatedEntity() {
        return relatedEntity;
    }

    public void setSquized(boolean squized) {
        this.squized = squized;
    }

    public boolean isSquized() {
        return squized;
    }
}
