package sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import data.Constants;
import data.GameDatabase;

/**
 * Game level. Handles rendering and collisions
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class Level {
    
    // data representation of the level mapping
    private Integer[][] levelMapData = new Integer[21][21];
    
    /**
     * Initializes the level data
     * @param levelNumber chosen level to initialize
     */
    public Level(Integer levelNumber) {
        
        GameDatabase gameData = GameDatabase.getInstance();
        this.levelMapData = gameData.getLevelMapData(levelNumber);
    }
    
    /**
     * Indicates if the level collides with a given sprite
     * @param sprite sprite to check collision
     * @return if the level collides with the given sprite
     */
    public Boolean collides(Sprite sprite) {
        Sprite tileSprite;
        Integer tileX, tileY;
        for (Integer i = 0; i < levelMapData.length; i++)
            for (Integer j = 0; j < levelMapData[i].length; j++) {
                
                tileX = levelMapData[i][j] * Constants.TILE_SIZE;
                tileY = i * Constants.TILE_SIZE;
                tileSprite = new Sprite(null, tileX, tileY);
                if (tileSprite.collides(sprite))
                    return true;
            }
        
        return false;
    }
            
    /**
     * Renders the level 
     * @param renderer rendering tool
     */ 
    public void render(Graphics2D renderer) {
        renderer.setColor(Color.WHITE);
        for (Integer i = 0; i < levelMapData.length; i++)
            for (Integer j = 0; j < levelMapData[i].length; j++) {
                renderer.drawRect(levelMapData[i][j] * Constants.TILE_SIZE, i * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
                //renderer.fillRect(levelData[i][j] * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
    }
}
