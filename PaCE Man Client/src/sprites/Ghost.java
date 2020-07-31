package sprites;

import java.awt.Image;
import javax.swing.ImageIcon;
import data.Constants;
import data.Direction;
import data.GameData;
import data.GhostPathData;
import data.Position;

/**
 * Ghost position. Handles its moving in a path logic
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class Ghost extends Sprite {
    
    // ghost speed
    private Integer speed = 3;
    
    // ghost color
    private Integer color;
    
    // indicates if the ghost is currently edible
    private boolean isEdible = false;
    
    // ghost moving direction
    private Direction direction;
    
    // indicates if the ghost is traversing the looping path
    private boolean insideLoop = false;
    
    // path to enter the looping path
    private Direction[] pathToEnterLoop;
    
    // path that is looped by the ghost infinitely
    private Direction[] loopingPath;
    
    // position in the path used as index to traverse the path as array
    private Integer positionInPath = 0;
    
    // holds the next position that the ghost is moving to in the path
    private Position nextPathPosition;
    
    /**
     * The constructor sets up the ghost given the level number and the ghost color
     * 
     * @param level level number
     * @param ghostColor ghost color
     */
    public Ghost(Integer level, Integer ghostColor) {
        super(null, Constants.DEFAULT_GHOST_POSITION.x, Constants.DEFAULT_GHOST_POSITION.y);
        super.sprite = getGhostSprite(ghostColor);
        
        this.color = ghostColor;
        
        setPathData(level, ghostColor);
        initializeNextPathPosition();
    }
    
    /**
     * Constructor to just display the ghost
     * @param ghostColor ghost color
     * @param x ghost x position
     * @param y ghost y position
     */
    public Ghost(Integer ghostColor, Integer x, Integer y) {
        super(null, x, y);
        super.sprite = getGhostSprite(ghostColor);
    }

    /**
     * Sets the ghost paths data
     * 
     * @param level level number
     * @param ghostColor ghost color
     */
    private void setPathData(Integer level, Integer ghostColor) {
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
        positionInPath += 1;
        
        if (insideLoop) {
            boolean finishedPathLoop = (positionInPath >= loopingPath.length);
            if (finishedPathLoop)
                positionInPath = 0;
            

            // update direction
            this.direction = loopingPath[positionInPath];
            
        } else {
            
            // not inside loop yet
            boolean enteredPathLoop = (positionInPath >= pathToEnterLoop.length);
            if(enteredPathLoop) {
                
                // just entered path loop
                positionInPath = 0;
                direction = loopingPath[positionInPath];
                insideLoop = true;
            } else {
                
                // advance in path to enter path loop
                direction = pathToEnterLoop[positionInPath];
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
    private Image getGhostSprite(Integer ghostColor) {
        String spriteName;
        switch(ghostColor) {
            case 1:
                spriteName = "ghost_blue.png";
                break;
            case 3:
                spriteName = "ghost_orange.png";
                break;
            case 2:
                spriteName = "ghost_pink.png";
                break;
            default:
                spriteName = "ghost_red.png";
        }
        return new ImageIcon("resources/" + spriteName).getImage();
    }
    
    /**
     * Sets the ghost edible state
     * 
     * @param isEdible ghost edible state
     */
    public void setIsEdible(boolean isEdible) {
        this.isEdible = isEdible;
        if (isEdible)
            super.sprite = new ImageIcon("resources/ghost_weak.png").getImage();
        else
            super.sprite = getGhostSprite(this.color);
    }
    
    public boolean isIsEdible() {
        return isEdible;
    }
    
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
