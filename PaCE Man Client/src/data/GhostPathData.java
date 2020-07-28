package data;

/**
 * Holds the needed path data to move a ghost
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
}
