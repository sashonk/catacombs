package ru.asocial.games.catacombs.renderers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.asocial.games.catacombs.Entity;
import ru.asocial.games.catacombs.PropertyKeys;

public class DefaultEntityRenderer implements EntityRenderer{
    @Override
    public void render(Entity entity, Batch batch, float parentAlpha) {
        TextureRegion region = entity.getProperty(PropertyKeys.TEXTURE_REGION, TextureRegion.class);
        float angle = entity.getRotation();
        batch.draw(region, entity.getX(), entity.getY(), entity.getWidth() / 2, entity.getHeight() / 2, entity.getWidth(), entity.getHeight(), entity.getScaleX(), entity.getScaleY(), angle);
    }
}
