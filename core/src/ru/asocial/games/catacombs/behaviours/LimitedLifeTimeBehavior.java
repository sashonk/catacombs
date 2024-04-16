package ru.asocial.games.catacombs.behaviours;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import ru.asocial.games.catacombs.Entity;
import ru.asocial.games.catacombs.events.DestroyEntityEvent;

public class LimitedLifeTimeBehavior implements Behaviour {

    private static final String PROP_TIME_LEFT = "time_left";
    private float timeLimit;

    public LimitedLifeTimeBehavior(float timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public void act(Entity entity, float delta) {
        Float timeLeft = entity.getPropertyOrDefault(PROP_TIME_LEFT, Float.class, timeLimit);
        timeLeft -= delta;

        if (timeLeft <= 0) {
            entity.addAction(Actions.removeActor());
            entity.fire(new DestroyEntityEvent());
        }

        entity.putProperty(PROP_TIME_LEFT, timeLeft);
    }
}
