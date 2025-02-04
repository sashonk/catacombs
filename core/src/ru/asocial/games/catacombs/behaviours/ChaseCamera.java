package ru.asocial.games.catacombs.behaviours;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ru.asocial.games.catacombs.Entity;

public class ChaseCamera implements Behaviour{

    private final Camera camera;

    public ChaseCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void act(Entity entity, float delta) {
        Vector3 camPos = camera.position;
        float x = entity.getX() - camPos.x;
        float y = entity.getY() - camPos.y;
        float k = 1f;

        float a = new Vector2(x, y).len2() * k;
        float ax = (float) (a * Math.cos(Math.atan2(y, x)));
        float ay = (float) (a * Math.sin(Math.atan2(y, x)));

        float vx = ax * delta;
        float vy = ay * delta;

        float dx = vx * delta;
        float dy = vy * delta;

        if (Math.abs(dx) > 0.03 || Math.abs(dy) > 0.03) {
            camera.translate(dx, dy, 0);
        }
    }
}
