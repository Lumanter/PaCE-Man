package data;

/**
 * Singleton class that holds the levels map and ghosts path data
 */
public class GameData {
    
    // class singleton instance
    private static GameData instance = null;
    
    // set of level map data
    private int[][][] levelsMapData;
    
    // set of ghosts path data
    private GhostPathData[][] ghostsPathData;
    
    // private constructor, loads all the data
    private GameData() { 
        initializeLevelsMapData();
        initializeGhostsPathData();
    }
    
    // singleton getInstance method, initializes the instances if it haven't been
    public static GameData getInstance() {
        if (instance == null)
            instance = new GameData();
        return instance;
    }
    
    // initializes the levels map data
    private void initializeLevelsMapData() {
        levelsMapData = new int[Constants.LEVELS][Constants.GRID_DIMENSION][Constants.GRID_DIMENSION];
        
        // level 1
        levelsMapData[0] = new int[][]{
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
    
    // initializes the ghosts path data
    private void initializeGhostsPathData() {
        ghostsPathData = new GhostPathData[Constants.LEVELS][Constants.GHOSTS_NUMBER]; 
        
        // level 1, red ghost
        ghostsPathData[0][GhostColor.RED] = new GhostPathData(
                new Direction[]{Direction.UP, Direction.UP, Direction.LEFT, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP}, 
                new Direction[]{Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.LEFT,Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT});
    }
    
    /**
     * Returns the map data of a given level
     * 
     * @param level given level
     * @return map data of the level
     */
    public int[][] getLevelMapData(int level) {
        return levelsMapData[level - 1];
    }
    
    /**
     * Returns the path data of a ghost
     * 
     * @param level game level number
     * @param ghostColor ghost color number
     * @return the correspondent ghost path data
     */
    public GhostPathData getGhostPathData(int level, int ghostColor) {
        return ghostsPathData[level - 1][ghostColor];
    }
}
