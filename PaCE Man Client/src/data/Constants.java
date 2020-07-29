
package data;

/**
 * Holds the game constants
 */
public class Constants {
    // size of the level display part of the window
    public final static int LEVEL_SIZE = 436;
    
    // miliseconds rate that the display is refreshed
    public final static int FRAME_DELAY = 40; 
    
    // all tiles are 20x20 pixels
    public final static int TILE_SIZE = 20;
    
    // number of available levels
    public final static int LEVELS = 3;
    
    // maximum number of ghosts
    public final static int GHOSTS_NUMBER = 4;
    
    // n grid dimension, being the grid nxn  
    public final static int GRID_DIMENSION = 21;
    
    // default spawning ghost position
    public final static Position DEFAULT_GHOST_POSITION = new Position(200, 180);
    
    // duration in miliseconds of the pill time state
    public final static int PILL_TIME_DURATION = 5000;
}
