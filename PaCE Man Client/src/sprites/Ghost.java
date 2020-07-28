package sprites;

import java.awt.Image;
import javax.swing.ImageIcon;
import movement.Constants;
import movement.Direction;
import movement.Position;


public class Ghost extends Sprite {
    
    private int speed = 3;
    
    private boolean insideLoop = false;
    private Direction direction;
    private Direction[] pathToEnterLoop;
    private Direction[] pathLoop;
    
    private int positionInPath = 0;
    
    private Position nextPathPosition;
    
    public Ghost() {
        super(new ImageIcon("resources/ghost_red.jpg").getImage(), 20 * 10, 20 * 9);
        
        // test data
        this.pathToEnterLoop = new Direction[]{Direction.UP, Direction.UP, Direction.LEFT, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.UP};
        this.pathLoop = new Direction[]{Direction.RIGHT, Direction.RIGHT, Direction.UP, Direction.UP, Direction.LEFT,Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.RIGHT, Direction.RIGHT};
        
        this.direction = pathToEnterLoop[positionInPath];
        this.nextPathPosition = new Position(super.pos.x, super.pos.y);
        updateNextPosition();
    }
    
    private void updateNextPosition() {
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
    
    public void move() {
        if (exceedsNextPositionAfterMoving()) {
            // move to next position
            super.pos = new Position(nextPathPosition.x, nextPathPosition.y);
            advanceInPath();
        } else {
            super.pos = getPositionAfterMoving();
        }
    }
    
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
 
    private void advanceInPath() {
        this.positionInPath += 1;
        if (this.insideLoop) {
            boolean finishedPathLoop = (positionInPath >= pathLoop.length);
            if (finishedPathLoop)
                this.positionInPath = 0;
            // restart position in path loop
            this.direction = pathLoop[this.positionInPath];
            
        } else {
            boolean enteredPathLoop = (positionInPath >= pathToEnterLoop.length);
            if(enteredPathLoop) {
                // just entered path loop
                this.positionInPath = 0;
                this.direction = pathLoop[positionInPath];
                this.insideLoop = true;
            } else {
                // advance in path to enter path loop
                this.direction = pathToEnterLoop[positionInPath];
            }
        }
        updateNextPosition();
    }
}
