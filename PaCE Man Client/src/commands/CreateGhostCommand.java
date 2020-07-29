package commands;

import app.PlayerView;
import sprites.Ghost;

/**
 * Command to create a new ghost in the player view
 */
public class CreateGhostCommand extends Command {

    // ghost of the color to create
    int ghostColor;
    
    /**
     * Constructor receives the color of the ghost to create
     * 
     * @param playerView a player view instance
     * @param ghostColor ghost of the color to create
     */
    public CreateGhostCommand(PlayerView playerView, int ghostColor) {
        super(playerView);
        this.ghostColor = ghostColor;
    }

    /**
     * Creates the new ghost in the player view
     */
    @Override
    public void execute() {
        super.playerView.getGhosts().add(new Ghost(super.playerView.getLevelNumber(), ghostColor));
    }
    
}
