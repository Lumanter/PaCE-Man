package commands;

import clientViews.PlayerView;

/**
 * Command to increment the game score
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class IncrementScoreCommand extends Command {

    // points to increment the score
    private final Integer increment;
    
    /**
     * Constructor receives the new ghost speed
     * 
     * @param playerView a player view instance
     * @param increment score increment
     */
    public IncrementScoreCommand(PlayerView playerView, Integer increment) {
        super(playerView);
        this.increment = increment;
    }

    /**
     * Increments the game score
     */
    @Override
    public void execute() {
        super.playerView.incrementScore(increment);
    }
    
}
