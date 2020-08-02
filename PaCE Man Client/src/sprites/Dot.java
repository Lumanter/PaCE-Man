package sprites;

import data.Constants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 * Representation of the map dots
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class Dot extends Sprite {
    
    // dot size
    private final Integer size = 7;
    
    /**
     * Constructor to set the dot position
     * 
     * @param position_x dot x location
     * @param position_y dot y location
     */
    public Dot(Integer position_x, Integer position_y) {
        super(null, position_x, position_y);
    }

    /**
     * Renders the dot
     * 
     * @param renderer render tool
     * @param context render context
     */
    @Override
    public void render(Graphics2D renderer, ImageObserver context) {
        renderer.setColor(Color.WHITE);
        renderer.fillOval(super.pos.x + ((Constants.TILE_SIZE - size) / 2) , super.pos.y + ((Constants.TILE_SIZE - size) / 2), size, size);
    }
}
