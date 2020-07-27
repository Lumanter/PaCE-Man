package sprites;

import java.awt.Color;
import java.awt.Graphics2D;

public class Level {
    private final int TILE_SIZE = 20;
    
    private int[][] levelData = new int[21][21];
    
    public Level() {
        
        int[][] level_1 = {
            {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20},
            {0,10,20},
            {0,2,3,4,6,7,8,10,12,13,14,16,17,18,20},
            {0,20},
            {0,2,3,4,6,8,9,10,11,12,14,16,17,18,20},
            {0,6,10,14,20},
            {0,1,2,3,4,6,7,8,10,12,13,14,16,17,18,19,20},
            {0,1,2,3,4,6,14,16,17,18,19,20},
            {0,1,2,3,4,6,8,9,11,12,14,16,17,18,19,20},
            {8,12},
            {0,1,2,3,4,6,8,9,10,11,12,14,16,17,18,19,20},
            {0,1,2,3,4,6,14,16,17,18,19,20},
            {0,1,2,3,4,6,8,9,10,11,12,14,16,17,18,19,20},
            {0,10,20},
            {0,2,3,4,6,7,8,10,12,13,14,16,17,18,20},
            {0,4,16,20},
            {0,1,2,4,6,8,9,10,11,12,14,16,18,19,20},
            {0,6,10,14,20},
            {0,2,3,4,5,6,7,8,10,12,13,14,15,16,17,18,20},
            {0,20},
            {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}
            };
        this.levelData = level_1;
    }
    
    public boolean collides(Sprite sprite) {
        Sprite tileSprite;
        int tileX, tileY;
        for (int i = 0; i < levelData.length; i++)
            for (int j = 0; j < levelData[i].length; j++) {
                
                tileX = levelData[i][j] * TILE_SIZE;
                tileY = i * TILE_SIZE;
                tileSprite = new Sprite(null, TILE_SIZE, tileX, tileY);
                if (tileSprite.collides(sprite))
                    return true;
            }
        
        return false;
    }
            
    
    public void render(Graphics2D renderer) {
        renderer.setColor(Color.WHITE);
        for (int i = 0; i < levelData.length; i++)
            for (int j = 0; j < levelData[i].length; j++) {
                renderer.drawRect(levelData[i][j] * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                //renderer.fillRect(levelData[i][j] * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
    }
}
