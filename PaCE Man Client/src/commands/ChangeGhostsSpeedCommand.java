package commands;

import app.PlayerView;
import java.util.ArrayList;
import sprites.Ghost;

/**
 * Command to change the speed of the ghosts
 */
public class ChangeGhostsSpeedCommand extends Command {

    // new speed of the ghosts
    private int newGhostsSpeed;
    
    /**
     * Constructor receives the new ghost speed
     * 
     * @param playerView a player view instance
     * @param newGhostsSpeed new ghost speed
     */
    public ChangeGhostsSpeedCommand(PlayerView playerView, int newGhostsSpeed) {
        super(playerView);
        this.newGhostsSpeed = newGhostsSpeed;
    }

    /**
     * Changes the ghosts speed to the new speed
     */
    @Override
    public void execute() {
        ArrayList<Ghost> ghosts = super.playerView.getGhosts();
        for (Ghost ghost : ghosts)
            ghost.setSpeed(newGhostsSpeed);
    }
    
}
