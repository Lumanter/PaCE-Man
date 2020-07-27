package sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Sprite {

    protected Image sprite;
    protected int size;
    protected int posX, posY;

    public Sprite(Image sprite, int size, int position_x, int position_y) {
        this.sprite = sprite;
        this.size = size;
        this.posX = position_x;
        this.posY = position_y;
    }
    
    public boolean collides(Sprite otherSprite) {
        Rectangle thisRectangle = new Rectangle(posX, posY, size, size);
        Rectangle otherRectangle = new Rectangle(otherSprite.getPosX(), otherSprite.getPosY(), otherSprite.getSize(), otherSprite.getSize());
        return thisRectangle.intersects(otherRectangle);
    }
    
    public void render(Graphics2D renderer, ImageObserver context) {
        renderer.drawImage(sprite, posX, posY, context);
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
