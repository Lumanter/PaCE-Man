package data;

/**
 * Holds the needed path data to move a ghost
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class GhostPathData {
    
    // path traversed before entering the loop
    private Direction[] pathToEnterLoop;
    
    // path looped by the ghost
    private Direction[] loopingPath;

    /**
     * The constructor sets both pre loop and looping path
     * 
     * @param pathToEnterLoop path traversed before entering the loop
     * @param loopingPath path looped by the ghost
     */
    public GhostPathData(Direction[] pathToEnterLoop, Direction[] loopingPath) {
        this.pathToEnterLoop = pathToEnterLoop;
        this.loopingPath = loopingPath;
    }

    public Direction[] getPathToEnterLoop() {
        return pathToEnterLoop;
    }

    public void setPathToEnterLoop(Direction[] pathToEnterLoop) {
        this.pathToEnterLoop = pathToEnterLoop;
    }

    public Direction[] getLoopingPath() {
        return loopingPath;
    }

    public void setLoopingPath(Direction[] loopingPath) {
        this.loopingPath = loopingPath;
    }
    
    /**
     * Reverse a path of directions
     * 
     * @param path path of directions
     */
    public static void reversePath(Direction[] path) {
        for (Integer i = 0; i < path.length; i++) {
            Direction direction = path[i];
            Direction reversedDirection = reverseDirection(direction);
            path[i] = reversedDirection;
        }
    }
    
    /**
     * Returns the reversed direction of a given direction
     * 
     * @param direction given direction
     * @return reversed direction
     */
    public static Direction reverseDirection(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.DOWN;
                
            case RIGHT:
                return Direction.LEFT;
                
            case DOWN:
                return Direction.UP;
            
            default:
                return Direction.RIGHT;
        }
    }
}
