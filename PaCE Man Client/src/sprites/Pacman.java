
package sprites;

import java.awt.Image;
import javax.swing.ImageIcon;
import pace.man.client.Direction;


public class Pacman extends Sprite {
    private final int PANEL_SIZE = 436;
    private final int SPEED = 4;
    private final int ANIMATION_FRAME_TIME = 100;
    
    private Direction currentDirection = Direction.RIGHT;
    private Direction nextDirection = null;
    
    private boolean hasMouthClosed = false;
    private int animationFrameTimer = 0;
    
    public Pacman(int position_x, int position_y) {
        super(new ImageIcon("src/resources/images/pacman_closed.png").getImage(), 20, position_x, position_y);
    }
    
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
        Sprite spriteOnNextMove = new Sprite(null, 20, super.posX + incrementX, super.posY + incrementY);
        return !level.collides(spriteOnNextMove);
    }
    
    private void moveInCurrentDirection() {
        // increment position depending on direciton
        switch(currentDirection){
            case RIGHT:
                super.posX += SPEED;
                break;
            case LEFT:
                super.posX -= SPEED;
                break;             
            case UP:
                super.posY -= SPEED;
                break;           
            case DOWN:
                super.posY += SPEED;  
        }
    }
    
    public void warpIfNecessary() {
        // warp pacman if out of limits
        if (super.posX < 0) {
            super.posX = PANEL_SIZE - super.size;
        } else if(super.posX + super.size > PANEL_SIZE) {
            super.posX = 0;
        } else if (super.posY < 0) {
            super.posY = PANEL_SIZE - super.size;
        } else if (super.posY + super.size > PANEL_SIZE) {
            super.posY = 0;
        }
    }
        
    
    public void updateAnimation() {
        if (animationFrameTimer >= ANIMATION_FRAME_TIME) {
            toggleAnimationFrame();
            // restart animation frame timer
            this.animationFrameTimer = 0;
        }
    }
    
    private void toggleAnimationFrame() {
        if (hasMouthClosed)
            super.sprite = getCurrentDirectionSprite();
        else
            super.sprite = new ImageIcon("src/resources/images/pacman_closed.png").getImage();
        hasMouthClosed = !hasMouthClosed;
    }
    
    private Image getCurrentDirectionSprite() {
        switch(currentDirection){
            case RIGHT:
                return new ImageIcon("src/resources/images/pacman_right.png").getImage();
     
            case LEFT:
                return new ImageIcon("src/resources/images/pacman_left.png").getImage();
                        
            case UP:
                return new ImageIcon("src/resources/images/pacman_up.png").getImage();
                    
            case DOWN:
                return new ImageIcon("src/resources/images/pacman_down.png").getImage();
            
            default:
                return new ImageIcon("src/resources/images/pacman_right.png").getImage();
        }
    }
    
    public void increaseAnimationTimer(int increment) {
        this.animationFrameTimer += increment;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
        if(!hasMouthClosed) {
            super.sprite = getCurrentDirectionSprite();
        }
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }
     
}
