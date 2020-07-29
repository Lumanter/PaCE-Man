package commands;

import app.PlayerView;
import data.Constants;
import data.Position;

/**
 * Command to place a new pill in the player view 
 */
public class PlacePillCommand extends Command {

    // new pill position
    private Position pillPosition;

    /**
     * Constructor receives the position of the pill to place
     * 
     * @param playerView a player view instance
     * @param x pill x coordinate in grid system
     * @param y pill y coordinate in grid system
     */
    public PlacePillCommand(PlayerView playerView, int x, int y) {
        super(playerView);
        // convert the coordinates from grid system based to real display coordinates
        this.pillPosition = new Position(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE);
    }

    /**
     * Places a new pill in the player view
     */
    @Override
    public void execute() {
        super.playerView.getPillManager().addPill(pillPosition.x, pillPosition.y);
    }
    
}
