package data;

import sprites.Sprite;
import java.util.ArrayList;
import sprites.Dot;

/**
 * Singleton class that holds the levels map and ghosts path data
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class GameDatabase {
    
    // class singleton instance
    private static GameDatabase instance = null;
    
    // set of level map data
    private Integer[][][] levelsMapData;
    
    // set of ghosts path data
    private GhostPathData[][] ghostsPathData;
    
    // set of dots data
    private Integer[][][] dotsData;
    
    /**
     * Private constructor, loads all the data
     */
    private GameDatabase() { 
        initializeLevelsMapData();
        initializeGhostsPathData();
        initializeDotsData();
    }
    
    /**
     * Singleton getInstance method, initializes the instances if it haven't been
     * @return singleton instance
     */
    public static GameDatabase getInstance() {
        if (instance == null)
            instance = new GameDatabase();
        return instance;
    }

    /**
     * Initializes the levels map data
     */
    private void initializeLevelsMapData() {
        levelsMapData = new Integer[Constants.LEVELS][Constants.GRID_DIMENSION][Constants.GRID_DIMENSION];
        
        // level 1
        levelsMapData[0] = new Integer[][]{
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
    }
    
    /**
     * Initializes the ghosts path data
     */
    private void initializeGhostsPathData() {
        ghostsPathData = new GhostPathData[Constants.LEVELS][Constants.GHOSTS_NUMBER]; 
        
        // level 1, red ghost
        ghostsPathData[0][GhostColor.RED] = new GhostPathData(
            new Direction[]{Direction.UP, Direction.UP, Direction.LEFT, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP}, 
            new Direction[]{Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.LEFT, Direction.LEFT}
        );
        
        // level 1, blue ghost
        ghostsPathData[0][GhostColor.BLUE] = new GhostPathData(
            new Direction[]{Direction.UP, Direction.UP, Direction.RIGHT}, 
            new Direction[]{Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN}
        );
        
        // level 1, pink ghost
        ghostsPathData[0][GhostColor.PINK] = new GhostPathData(
            new Direction[]{Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN}, 
            new Direction[]{Direction.DOWN, Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT}
        );
        
        // level 1, orange ghost
        ghostsPathData[0][GhostColor.ORANGE] = new GhostPathData(
            new Direction[]{Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN}, 
            new Direction[]{Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.DOWN, Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT}
        );
        
    }
    
    /**
     * Initializes the dots data for each level
     */
    public void initializeDotsData() {
        dotsData = new Integer[Constants.LEVELS][Constants.GRID_DIMENSION][Constants.GRID_DIMENSION];
        // level 1 dots
        dotsData[0] = new Integer[][]{
            {},
            {1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19},
            {1,5,9,11,15,19},
            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19},
            {1,5,7,13,15,19},
            {1,2,3,4,5,7,8,9,11,12,13,15,16,17,18,19},
            {5,9,11,15},
            {5,7,8,9,10,11,12,13,15},
            {5,7,13,15},
            {},
            {5,7,13,15},
            {5,7,8,9,10,11,12,13,15},
            {5,7,13,15},
            {1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19},
            {1,5,9,11,15,19},
            {1,2,3,5,6,7,8,9,10,11,12,13,14,15,17,18,19},
            {3,5,7,13,15,17},
            {1,2,3,4,5,7,8,9,11,12,13,15,16,17,18,19},
            {1,9,11,19},
            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19},
            {}
            };
    }
    
    /**
     * Returns the map data of a given level
     * 
     * @param level given level
     * @return map data of the level
     */
    public Integer[][] getLevelMapData(Integer level) {
        return levelsMapData[level - 1];
    }
    
    /**
     * Returns the path data of a ghost
     * 
     * @param level game level number
     * @param ghostColor ghost color number
     * @return the corespondent ghost path data
     */
    public GhostPathData getGhostPathData(Integer level, Integer ghostColor) {
        return ghostsPathData[level - 1][ghostColor];
    }
    
    /**
     * Returns the dots of a given level
     * @param level given level
     * @return list of dots
     */
    public ArrayList<Sprite> getDots(Integer level) {
        ArrayList<Sprite> dots = new ArrayList<>();
        Integer[][] rawDots = dotsData[level - 1];
        
        for (int i = 0; i < rawDots.length; i++)
            for (int j = 0; j < rawDots[i].length; j++)
                dots.add(new Dot(rawDots[i][j] * Constants.TILE_SIZE, i * Constants.TILE_SIZE));
                
        return dots;
    }
}
