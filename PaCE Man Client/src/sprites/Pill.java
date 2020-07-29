package sprites;

import data.Constants;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class Pill extends Sprite {
    
    // pill size
    private final int size = 15;
    
    /**
     * Constructor to set the pill position
     * 
     * @param position_x pill x location
     * @param position_y pill y location
     */
    public Pill(int position_x, int position_y) {
        super(null, position_x, position_y);
    }

    /**
     * Renders the power pill
     * 
     * @param renderer render tool
     * @param context render context
     */
    @Override
    public void render(Graphics2D renderer, ImageObserver context) {
        renderer.setColor(Color.YELLOW);
        renderer.fillOval(super.pos.x + ((Constants.TILE_SIZE - size) / 2) , super.pos.y + ((Constants.TILE_SIZE - size) / 2), size, size);
    }

    
}
