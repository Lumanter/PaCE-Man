package commands;

import clientViews.PlayerView;

/**
 * Command to execute an action on a player view instance
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public abstract class Command {
    
    // player view instance to execute action
    protected PlayerView playerView;

    /**
     * The constructor receives the player view instance to execute the command
     * @param playerView a player view instance
     */
    public Command(PlayerView playerView) {
        this.playerView = playerView;
    }
    
    /**
     * Executes the command action
     */
    public abstract void execute();
}
