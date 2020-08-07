package sprites;

import data.Position;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Manages a list of sprites
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class SpriteManager {
    
    // list of displayed sprites
    private ArrayList<Sprite> sprites = new ArrayList<>();
    
    /**
     * Adds a sprite in the given position with a value
     * 
     * @param sprite sprite to add
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
    
    /**
     * Removes the sprite in the given position
     * @param spritePosition 
     */
    public void removeSprite(Position spritePosition) {
        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = sprites.get(i);
            if (sprite.getPos() == spritePosition) {
                sprites.remove(i);
                break;
            }
        }
    }
    
    /**
     * Renders all the sprites
     * 
     * @param renderer rendering tool
     */
    public void render(Graphics2D renderer) {
        for(Sprite sprite : sprites)
            sprite.render(renderer, null);
    }
    
    /**
     * Indicates if the given sprite collides with a the lists of sprites. 
     * If it does the sprite position is returned, otherwise null is returned
     * 
     * @param otherSprite sprite to check collision
     * @return collided sprite position
     */
    public Position hasCollision(Sprite otherSprite) {
        for(Sprite sprite : sprites)
            if(otherSprite.collides(sprite))
                return sprite.getPos();
        return null;
    }
    
    /**
     * Returns the sprite at the given position
     * 
     * @param position given position
     * @return sprite at the given position
     */
    public Sprite getSprite(Position position) {
        for (Sprite sprite : sprites)
            if (sprite.getPos() == position)
                return sprite;
        return null;
    }

    public void setSprites(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}
