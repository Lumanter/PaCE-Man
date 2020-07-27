
package sprites;

import java.awt.Image;
import javax.swing.ImageIcon;
import main.Direction;

/**
 * Pacman representation. Handles collisions and animation
 */
public class Pacman extends Sprite {
    
    // side of display panel, used for warp
    private final int PANEL_SIZE = 436;
    
    // pacman speed
    private final int SPEED = 4;
    
    // time that each animation frame lasts for
    private final int ANIMATION_FRAME_TIME = 100;
    
    // pacman current facing direction
    private Direction currentDirection = Direction.RIGHT;
    
    // pacman desired next direction, used for smooth movement
    private Direction nextDirection = null;
    
    // timer used for animation frame toggle
    private int animationFrameTimer = 0;
    
    /**
     * Constructor to set pacman initial position
     * 
     * @param position_x pacman x position
     * @param position_y pacman y position
     */
    public Pacman(int position_x, int position_y) {
        super(new ImageIcon("resources/pacman_closed.png").getImage(), 20, position_x, position_y);
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
    private boolean notCollidesOnNextMove(Level level, Direction direction) {
        int incrementX = 0;
        int incrementY = 0;
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
        Sprite spriteOnNextMove = new Sprite(null, 20, super.x + incrementX, super.y + incrementY);
        return !level.collides(spriteOnNextMove);
    }
    
    /**
     * Moves pacman with the current position
     */
    private void moveInCurrentDirection() {
        switch(currentDirection){
            case RIGHT:
                super.x += SPEED;
                break;
            case LEFT:
                super.x -= SPEED;
                break;             
            case UP:
                super.y -= SPEED;
                break;           
            case DOWN:
                super.y += SPEED;  
        }
    }
    
    /**
     * Warps pacman if it is out of limits to the opposite side of the screen
     */
    public void warpIfNecessary() {
        if (super.x < 0) {
            super.x = PANEL_SIZE - super.size;
        } else if(super.x + super.size > PANEL_SIZE) {
            super.x = 0;
        } else if (super.y < 0) {
            super.y = PANEL_SIZE - super.size;
        } else if (super.y + super.size > PANEL_SIZE) {
            super.y = 0;
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
        boolean inMouthClosedFrame = (getAnimationNumber() == 0);
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
     * Increases the animation timer 
     * @param increment timer increment
     */
    public void increaseAnimationTimer(int increment) {
        this.animationFrameTimer += increment;
    }
    
    /**
     * Sets the animation frame with its number representation
     * 
     * @param animationNumber animation frame number representation
     */
    public void setAnimationSprite(int animationNumber) {
        switch(animationNumber){
            case 0:
                super.sprite = new ImageIcon("resources/pacman_closed.png").getImage();
            case 1:
                super.sprite = new ImageIcon("resources/pacman_up.png").getImage();
            case 2:
                super.sprite = new ImageIcon("resources/pacman_right.png").getImage();
            case 3:
                super.sprite = new ImageIcon("resources/pacman_down.png").getImage();
            case 4:
                super.sprite = new ImageIcon("resources/pacman_left.png").getImage();
        }
    }
    
    /**
     * Returns animation frame number representation
     * @return animation frame number representation
     */
    public int getAnimationNumber() {
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

    /**
     * Sets pacman position
     * 
     * @param x pacman x position
     * @param y pacman y position
     */
    public void setPosition(int x, int y) {
        super.x = x;
        super.y = y;
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
