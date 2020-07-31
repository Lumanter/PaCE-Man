package sprites;

import data.Constants;
import data.Position;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Manages the pills to eat ghosts. Handles the pill time timer
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class PillManager {
    
    // indicates is a pill is active
    private boolean pillActive = false;
    
    // list of displayed pills
    private final ArrayList<Pill> pills = new ArrayList<>();
    
    // timer to activate pill time
    private Integer pillActiveTimer = Constants.PILL_TIME_DURATION;
    
    /**
     * Adds a pill in the given position
     * 
     * @param x pill x position
     * @param y pill y position
     */
    public void addPill(Integer x, Integer y) {
        pills.add(new Pill(x, y));
    }
    
    /**
     * Removes the pill in the given position
     * 
     * @param pillPosition pill position
     */
    public void removePill(Position pillPosition) {
        for (Integer i = 0; i < pills.size(); i++) {
            Pill pill = pills.get(i);
            if (pill.getPos() == pillPosition) {
                pills.remove(i);
                break;
            }
        }
    }
    
    /**
     * Renders all the pills
     * 
     * @param renderer rendering tool
     */
    public void render(Graphics2D renderer) {
        for(Pill pill : pills)
            pill.render(renderer, null);
    }

    /**
     * Indicates if there's a pill active
     * @return 
     */
    public boolean isPillActive() {
        return pillActive;
    }

    public void setPillActive(boolean pillActive) {
        this.pillActive = pillActive;
    }
    
    /**
     * Indicates if the given sprite collides with a pill. 
     * If it does the pill position is returned, otherwise null is returned
     * 
     * @param sprite sprite to check collision
     * @return collided pill position
     */
    public Position hasCollision(Sprite sprite) {
        for(Pill pill : pills)
            if(pill.collides(sprite))
                return pill.getPos();
        return null;
    }
    
    /**
     * Decreases the time in the pill time timer by the frame delay constant
     * 
     * @return the remaining pill time
     */
    public Integer tickTimer() {
        pillActiveTimer -= Constants.FRAME_DELAY;
        if(pillActiveTimer <= 0) {
            pillActiveTimer = Constants.PILL_TIME_DURATION;
            pillActive = false;
            return 0;
        } else {
            return pillActiveTimer;
        }
    }

    public ArrayList<Pill> getPills() {
        return pills;
    }
}
