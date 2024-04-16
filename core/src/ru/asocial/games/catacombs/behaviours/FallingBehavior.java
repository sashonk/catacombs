package ru.asocial.games.catacombs.behaviours;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import ru.asocial.games.catacombs.Entity;
import ru.asocial.games.catacombs.Layers;
import ru.asocial.games.catacombs.PropertyKeys;
import ru.asocial.games.catacombs.events.DestroyEntityEvent;
import ru.asocial.games.catacombs.events.ExplodeEntityEvent;

public class FallingBehavior extends MovingBehavior{
    public FallingBehavior(Layers layers) {
        super(layers);
    }

    @Override
    protected Vector2 findNextMove(Entity entity) {
        Vector2 move = new Vector2(0, -1);
        int cx = (int) (entity.getX() / entity.getWidth() + move.x);
        int cy = (int) (entity.getY() / entity.getHeight() + move.y);
        if (isCellFree(cx, cy)) {
            Float delay = entity.getProperty(PropertyKeys.DELAY, Float.class);
            if (delay != null) {
                if (delay <= 0) {
                    entity.putProperty(PropertyKeys.IS_ROLLING, false);
                    entity.putProperty(PropertyKeys.IS_FALLING, true);
                    incrementFallingEntitiesCounter(entity);
                    return move;
                }

                entity.putProperty(PropertyKeys.DELAY, delay - Gdx.graphics.getDeltaTime());
                return null;
            }
            entity.putProperty(PropertyKeys.IS_ROLLING, false);
            entity.putProperty(PropertyKeys.IS_FALLING, true);
            incrementFallingEntitiesCounter(entity);
            return move;
        }
        else {
            if (entity.getPropertyOrDefault(PropertyKeys.IS_FALLING, Boolean.class, false)
                    && entity.getPropertyOrDefault(PropertyKeys.IS_EXPLOSIVE, Boolean.class, false)) {
                entity.fire(new ExplodeEntityEvent());
            }
        }

        entity.putProperty(PropertyKeys.DELAY, 0.1f);

        Entity e = getObjectAtCell(entity, move);
        if (e != null && entity.getPropertyOrDefault(PropertyKeys.IS_FALLING, Boolean.class, false)) {
            if (e.getPropertyOrDefault(PropertyKeys.IS_EXPLOSIVE, Boolean.class, false)) {
                e.fire(new ExplodeEntityEvent());
            }

            if (e.getPropertyOrDefault(PropertyKeys.IS_SQUIZABLE, Boolean.class, false)) {
                freeObject(e);
                e.addAction(Actions.removeActor());
                DestroyEntityEvent event = new DestroyEntityEvent();
                event.setRelatedEntity(entity);
                event.setSquized(true);
                e.fire(event);
            }
        }

        entity.putProperty(PropertyKeys.IS_FALLING, false);
        decrementFallingEntitiesCounter(entity);
        return null;
    }
}
