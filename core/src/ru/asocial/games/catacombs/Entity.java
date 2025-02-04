package ru.asocial.games.catacombs;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.asocial.games.catacombs.behaviours.Behaviour;
import ru.asocial.games.catacombs.renderers.EntityRenderer;

import java.util.*;

public class Entity extends Actor {

    private List<Behaviour> behaviours = new LinkedList<>();
    private EntityRenderer renderer;

    private Map<String, Object> properties = new HashMap<>();

    public boolean hasBehaviour(Class<? extends Behaviour> cls) {
        return behaviours.stream().anyMatch(behaviour -> cls.isAssignableFrom(behaviour.getClass()));
    }

    public <T extends Behaviour> Optional<T> findBehavior(Class<T> behaviorClass) {
        return (Optional<T>) behaviours.stream().filter(behaviour -> behaviorClass.isAssignableFrom(behaviour.getClass())).findFirst();
    }

    public String getType() {
        return (String) properties.get("type");
    }

    public void removeBehaviours(Class<? extends Behaviour> cls) {
        behaviours.removeIf(behaviour -> cls.isAssignableFrom(behaviour.getClass()));
    }

    public void setRenderer(EntityRenderer renderer) {
        this.renderer = renderer;
    }

    public EntityRenderer getRenderer() {
        return renderer;
    }

    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){

        renderer.render(this, batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        behaviours.forEach(behaviour -> behaviour.act(this, delta));
    }

    public Map<String, Object> getAllProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public void putProperty(String key, Object value) {
        properties.put(key, value);
    }

    public void removeProperty(String key) {
        properties.remove(key);
    }

    public <T> T getProperty(String key, Class<T> cls) {
        Object value = properties.get(key);
        if (value == null) {
            return null;
        }
        if (cls.isAssignableFrom(value.getClass())) {
            return (T) value;
        }

        throw new ClassCastException(value.getClass() + " can not be assigned to " + cls);
    }

    public <T> T getPropertyOrDefault(String key, Class<T> cls, T def) {
        T value = getProperty(key, cls);
        return value != null ? value : def;
    }

}
