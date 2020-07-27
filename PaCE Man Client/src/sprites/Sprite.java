package sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Simple representation of a squared sprite to display. Handles rendering and collisions
 */
public class Sprite {
    
    // sprite image
    protected Image sprite;
    
    // sprite side size
    protected int size;
    
    // sprite x and y location
    protected int x, y;

    /**
     * Constructor to set all variables
     * 
     * @param sprite sprite image
     * @param size sprite side size
     * @param position_x sprite x location
     * @param position_y sprite y location
     */
    public Sprite(Image sprite, int size, int position_x, int position_y) {
        this.sprite = sprite;
        this.size = size;
        this.x = position_x;
        this.y = position_y;
    }
    
    /**
     * Indicates collision with other sprite
     * 
     * @param otherSprite sprite to check collision
     * @return if this sprite collides with the other sprite
     */
    public boolean collides(Sprite otherSprite) {
        Rectangle thisRectangle = new Rectangle(x, y, size, size);
        Rectangle otherRectangle = new Rectangle(otherSprite.getX(), otherSprite.getY(), otherSprite.getSize(), otherSprite.getSize());
        return thisRectangle.intersects(otherRectangle);
    }
    
    /**
     * Renders the sprite
     * 
     * @param renderer render tool
     * @param context render context
     */
    public void render(Graphics2D renderer, ImageObserver context) {
        renderer.drawImage(sprite, x, y, context);
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
