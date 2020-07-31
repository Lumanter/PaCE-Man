
package sprites;

import java.awt.Image;
import javax.swing.ImageIcon;
import data.Constants;
import data.Direction;
import data.Position;

/**
 * Pacman representation. Handles collisions and animation
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class Pacman extends Sprite {
    
    // pacman speed
    private final Integer SPEED = 4;
    
    // time that each animation frame lasts for
    private final Integer ANIMATION_FRAME_TIME = 100;
    
    // pacman current facing direction
    private Direction currentDirection = Direction.RIGHT;
    
    // pacman desired next direction, used for smooth movement
    private Direction nextDirection = null;
    
    // timer used for animation frame toggle
    private Integer animationFrameTimer = 0;
    
    /**
     * Constructor that sets pacman to the default position
     */
    public Pacman() {
        super(new ImageIcon("resources/pacman_closed.png").getImage(), Constants.DEFAULT_PACMAN_POSITION.x, Constants.DEFAULT_PACMAN_POSITION.y);
    }
    
    /**
     * Constructor that sets pacman position and animation frame
     * 
     * @param x pacman initial x position
     * @param y pacman initial y position
     * @param animationNumber indicates pacman's current animation sprite
     */
    public Pacman(Integer x, Integer y, Integer animationNumber) {
        super(null, x, y);
        this.setAnimationSprite(animationNumber);
    }
    
    /**
     * Resets pacman to its default position
     */
    public void resetPosition() {
        super.pos = Constants.DEFAULT_PACMAN_POSITION;
        this.currentDirection = Direction.RIGHT;
    }
    
    /**
     * Moves pacman position. Checks for collisions and direction changes
     * 
     * @param level level that pacman is in 
     */
    public void move(Level level) {  
        if (nextDirection != null && notCollidesOnNextMove(level, nextDirection)) {
            if (notCollidesOnNextMove(level, nextDirection)) {
                this.currentDirection = nextDirection;
                this.nextDirection = null;
                moveInCurrentDirection();
            } else {
                if (notCollidesOnNextMove(level, this.currentDirection)) 
                moveInCurrentDirection();
            }
        } else {
            if (notCollidesOnNextMove(level, this.currentDirection)) 
                moveInCurrentDirection();
        }
    }
    
    /**
     * Indicates if pacman will collide with the level if it moves in the given direction
     * 
     * @param level level to check collision
     * @param direction direction to check collision
     * @return if pacman will collide with the level if it moves in the direction
     */
    private Boolean notCollidesOnNextMove(Level level, Direction direction) {
        Integer incrementX = 0;
        Integer incrementY = 0;
        switch(direction){
            case RIGHT:
                incrementX = SPEED;
                break;
            case LEFT:
                incrementX = -SPEED;
                break;             
            case UP:
                incrementY = -SPEED;
                break;           
            case DOWN:
                incrementY = SPEED;  
        }
        Sprite spriteOnNextMove = new Sprite(null, super.pos.x + incrementX, super.pos.y + incrementY);
        return !level.collides(spriteOnNextMove);
    }
    
    /**
     * Moves pacman with the current position
     */
    private void moveInCurrentDirection() {
        switch(currentDirection){
            case RIGHT:
                super.pos.x += SPEED;
                break;
            case LEFT:
                super.pos.x -= SPEED;
                break;             
            case UP:
                super.pos.y -= SPEED;
                break;           
            case DOWN:
                super.pos.y += SPEED;  
        }
    }
    
    /**
     * Warps pacman if it is out of limits to the opposite side of the screen
     */
    public void warpIfNecessary() {
        if (super.pos.x < 0) {
            super.pos.x = Constants.LEVEL_SIZE - Constants.TILE_SIZE;
        } else if(super.pos.x + Constants.TILE_SIZE > Constants.LEVEL_SIZE) {
            super.pos.x = 0;
        } else if (super.pos.y < 0) {
            super.pos.y = Constants.LEVEL_SIZE - Constants.TILE_SIZE;
        } else if (super.pos.y + Constants.TILE_SIZE > Constants.LEVEL_SIZE) {
            super.pos.y = 0;
        }
    }
        
    /**
     * Updates pacman animation frame depending on the animation timer state
     */
    public void updateAnimation() {
        if (animationFrameTimer >= ANIMATION_FRAME_TIME) {
            toggleAnimationFrame();
            // restart animation frame timer
            this.animationFrameTimer = 0;
        }
    }
    
    /**
     * Toggles between closed mouth and open mouth animation frames
     */
    private void toggleAnimationFrame() {
        Boolean inMouthClosedFrame = (getAnimationNumber() == 0);
        if (inMouthClosedFrame)
            super.sprite = getCurrentDirectionSprite();
        else
            super.sprite = new ImageIcon("resources/pacman_closed.png").getImage();
    }
    
    /**
     * Returns the animation frame corresponding to the current direction
     * 
     * @return pacman animation frame corresponding to the current direction
     */
    private Image getCurrentDirectionSprite() {
        switch(currentDirection){
            case RIGHT:
                return new ImageIcon("resources/pacman_right.png").getImage();
                
            case LEFT:
                return new ImageIcon("resources/pacman_left.png").getImage();
                        
            case UP:
                return new ImageIcon("resources/pacman_up.png").getImage();
                    
            case DOWN:
                return new ImageIcon("resources/pacman_down.png").getImage();
            
            default:
                return new ImageIcon("resources/pacman_right.png").getImage();
        }
    }
    
    /**
     * Increases the animation timer by the frame delay constant
     */
    public void increaseAnimationTimer() {
        this.animationFrameTimer += Constants.FRAME_DELAY;
    }
    
    /**
     * Sets the animation frame with its number representation
     * 
     * @param animationNumber animation frame number representation
     */
    public void setAnimationSprite(Integer animationNumber) {
        switch(animationNumber){
            case 0:
                super.sprite = new ImageIcon("resources/pacman_closed.png").getImage();
                break;
            case 1:
                super.sprite = new ImageIcon("resources/pacman_up.png").getImage();
                break;
            case 2:
                super.sprite = new ImageIcon("resources/pacman_right.png").getImage();
                break;
            case 3:
                super.sprite = new ImageIcon("resources/pacman_down.png").getImage();
                break;
            case 4:
                super.sprite = new ImageIcon("resources/pacman_left.png").getImage();
        }
    }
    
    /**
     * Returns animation frame number representation
     * @return animation frame number representation
     */
    public Integer getAnimationNumber() {
        if (super.sprite == new ImageIcon("resources/pacman_up.png").getImage())
            return 1;
        if (super.sprite == new ImageIcon("resources/pacman_right.png").getImage())
            return 2;
        if (super.sprite == new ImageIcon("resources/pacman_down.png").getImage())
            return 3;
        if (super.sprite == new ImageIcon("resources/pacman_left.png").getImage())
            return 4;
        return 0;
    }
    
    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
        
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    
    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }
}
