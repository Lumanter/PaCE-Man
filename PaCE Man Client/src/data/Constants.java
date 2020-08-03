
package data;

/**
 * Holds the game constants
 * @author Luis Mariano Ramírez Segura - github/Lumanter 
 */
public class Constants {
    // size of the level display part of the window
    public final static Integer LEVEL_SIZE = 436;
    
    // miliseconds rate that the display is refreshed
    public final static Integer FRAME_DELAY = 40; 
    
    // all tiles are 20x20 pixels
    public final static Integer TILE_SIZE = 20;
    
    // number of available levels
    public final static Integer LEVELS = 3;
    
    // maximum number of ghosts
    public final static Integer GHOSTS_NUMBER = 4;
    
    // n grid dimension, being the grid nxn  
    public final static Integer GRID_DIMENSION = 21;
    
    // default pacman position
    public final static Position DEFAULT_PACMAN_POSITION = new Position(200, 300);
    
    // default spawning ghost position
    public final static Position DEFAULT_GHOST_POSITION = new Position(200, 180);
    
    // duration in miliseconds of the pill time state
    public final static Integer PILL_TIME_DURATION = 5000;
    
    // points obtained from eating a dot
    public final static Integer DOT_POINTS = 10;
    
    // points obtained from eating a ghost
    public final static Integer GHOST_POINTS = 500;
    
    // points obtained from eating a pill
    public final static Integer PILL_POINTS = 100;
    
    // default game lifes
    public final static Integer DEFAULT_LIFES = 3;
}
