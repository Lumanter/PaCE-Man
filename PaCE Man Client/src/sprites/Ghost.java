package sprites;

import java.awt.Image;
import javax.swing.ImageIcon;
import data.Constants;
import data.Direction;
import data.GameData;
import data.GhostColor;
import data.GhostPathData;
import data.Position;

/**
 * Ghost position. Handles its moving in a path logic
 */
public class Ghost extends Sprite {
    
    // ghost speed
    private int speed = 3;
    
    // ghost moving direction
    private Direction direction;
    
    // indicates if the ghost is traversing the looping path
    private boolean insideLoop = false;
    
    // path to enter the looping path
    private Direction[] pathToEnterLoop;
    
    // path that is looped by the ghost infinitely
    private Direction[] loopingPath;
    
    // position in the path used as index to traverse the path as array
    private int positionInPath = 0;
    
    // holds the next position that the ghost is moving to in the path
    private Position nextPathPosition;
    
    /**
     * The constructor sets up the ghost given the level number and the ghost color
     * 
     * @param level level number
     * @param ghostColor ghost color
     */
    public Ghost(int level, int ghostColor) {
        super(null, Constants.DEFAULT_GHOST_POSITION.x, Constants.DEFAULT_GHOST_POSITION.y);
        super.sprite = getGhostSprite(ghostColor);
        
        setPathData(level, ghostColor);
        initializeNextPathPosition();
    }

    /**
     * Sets the ghost paths data
     * 
     * @param level level number
     * @param ghostColor ghost color
     */
    private void setPathData(int level, int ghostColor) {
        GameData gameData = GameData.getInstance();
        GhostPathData ghostPathData = gameData.getGhostPathData(level, ghostColor);
        this.pathToEnterLoop = ghostPathData.getPathToEnterLoop();
        this.loopingPath = ghostPathData.getLoopingPath();
    }
    
    /**
     * Initializes the next path position with the set path data
     */
    private void initializeNextPathPosition() {
        this.direction = pathToEnterLoop[positionInPath];
        this.nextPathPosition = new Position(super.pos.x, super.pos.y);
        updateNextPathPosition();
    }
    
    /**
     * Updates the desired next path position with the current direction
     */
    private void updateNextPathPosition() {
        switch (this.direction) {
            case UP:
                this.nextPathPosition.y -= Constants.TILE_SIZE;
                break;
            case RIGHT:
                this.nextPathPosition.x += Constants.TILE_SIZE;
                break;
            case DOWN:
                this.nextPathPosition.y += Constants.TILE_SIZE;
                break;
            case LEFT:
                this.nextPathPosition.x -= Constants.TILE_SIZE;
        }
    }
    
    /**
     * Executes the ghost moving logic, either advancing to the next path position 
     * or moving in the current direction
     */
    public void move() {
        if (exceedsNextPositionAfterMoving()) {
            // move to next position
            super.pos = new Position(nextPathPosition.x, nextPathPosition.y);
            advanceInPath();
        } else {
            super.pos = getPositionAfterMoving();
        }
    }
    
    /**
     * Indicates if the ghost exceeds the desired next path position if it moves in the current direction
     * 
     * @return if the ghost exceeds the desired next path position if it moves in the current direction
     */
    private boolean exceedsNextPositionAfterMoving() {
        Position nextMovePos = getPositionAfterMoving();
        switch (this.direction) {
            case UP:
                if(nextMovePos.y < nextPathPosition.y)
                    return true;
                break;
            case RIGHT:
                if(nextMovePos.x > nextPathPosition.x)
                    return true;
                break;
            case DOWN:
                if(nextMovePos.y > nextPathPosition.y)
                    return true;
                break;
            case LEFT:
                if(nextMovePos.x < nextPathPosition.x)
                    return true; 
                break;
        }
        // move don't exceeds nextPos
        return false;
    }
    
    /**
     * Returns the ghost position after moving in the current direction
     * 
     * @return ghost position after moving in the current direction
     */
    private Position getPositionAfterMoving() {
        Position positionUponMoving = new Position(super.pos.x, super.pos.y);
        switch (this.direction) {
            case UP:
                positionUponMoving.y -= speed;
                break;
            case RIGHT:
                positionUponMoving.x += speed;
                break;
            case DOWN:
                positionUponMoving.y += speed;
                break;
            case LEFT:
                positionUponMoving.x -= speed;  
        }
        return positionUponMoving;
    }
 
    /**
     * Advances the ghost in the path being traversed, either pre loop or loop
     */
    private void advanceInPath() {
        this.positionInPath += 1;
        if (this.insideLoop) {
            boolean finishedPathLoop = (positionInPath >= loopingPath.length);
            if (finishedPathLoop)
                this.positionInPath = 0;
            // restart position in path loop
            this.direction = loopingPath[this.positionInPath];
            
        } else {
            boolean enteredPathLoop = (positionInPath >= pathToEnterLoop.length);
            if(enteredPathLoop) {
                // just entered path loop
                this.positionInPath = 0;
                this.direction = loopingPath[positionInPath];
                this.insideLoop = true;
            } else {
                // advance in path to enter path loop
                this.direction = pathToEnterLoop[positionInPath];
            }
        }
        updateNextPathPosition();
    }
    
    /**
     * Returns the ghost sprite for a given color
     * 
     * @param ghostColor given color
     * @return ghost sprite of the given color
     */
    private Image getGhostSprite(int ghostColor) {
        String spriteName;
        switch(ghostColor) {
            case GhostColor.BLUE:
                spriteName = "ghost_blue.jpg";
                break;
            case GhostColor.ORANGE:
                spriteName = "ghost_orange.jpg";
                break;
            case GhostColor.PINK:
                spriteName = "ghost_pink.jpg";
                break;
            default:
                spriteName = "ghost_red.jpg";
        }
        return new ImageIcon("resources/" + spriteName).getImage();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
