package ru.asocial.games.catacombs.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.asocial.games.catacombs.Entity;

public interface EntityRenderer {

    void render(Entity entity, Batch batch, float parentAlpha);
}
