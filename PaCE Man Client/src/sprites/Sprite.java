package sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import movement.Constants;
import movement.Position;

/**
 * Simple representation of a squared sprite to display. Handles rendering and collisions
 */
public class Sprite {
    
    // sprite image
    protected Image sprite;
    
    // sprite position
    protected Position pos;

    /**
     * Constructor to set all variables
     * 
     * @param sprite sprite image
     * @param position_x sprite x location
     * @param position_y sprite y location
     */
    public Sprite(Image sprite, int position_x, int position_y) {
        this.sprite = sprite;
        this.pos = new Position(position_x, position_y);
    }
    
    /**
     * Indicates collision with other sprite
     * 
     * @param otherSprite sprite to check collision
     * @return if this sprite collides with the other sprite
     */
    public boolean collides(Sprite otherSprite) {
        Rectangle thisRectangle = new Rectangle(pos.x, pos.y, Constants.TILE_SIZE, Constants.TILE_SIZE);
        Rectangle otherRectangle = new Rectangle(otherSprite.getPos().x, otherSprite.getPos().y, Constants.TILE_SIZE, Constants.TILE_SIZE);
        return thisRectangle.intersects(otherRectangle);
    }
    
    /**
     * Renders the sprite
     * 
     * @param renderer render tool
     * @param context render context
     */
    public void render(Graphics2D renderer, ImageObserver context) {
        renderer.drawImage(sprite, pos.x, pos.y, context);
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
