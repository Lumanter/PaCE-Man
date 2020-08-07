package commands;

import clientViews.PlayerView;
import data.Constants;
import sprites.Fruit;

/**
 * Command to create a new fruit in the player view
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class CreateFruitCommand extends Command {

    // create fruit
    private Fruit fruit;
    
    /**
     * Constructor receives the fruit value and position
     * 
     * @param playerView a player view instance
     * @param points fruit points value
     * @param position_x fruit x position
     * @param position_y fruit y position
     */
    public CreateFruitCommand(PlayerView playerView, Integer points, Integer position_x, Integer position_y) {
        super(playerView);
        this.fruit = new Fruit(points, position_x * Constants.TILE_SIZE, position_y * Constants.TILE_SIZE);
    }

    /**
     * Adds a new fruit in the player view
     */
    @Override
    public void execute() {
        super.playerView.getFruitManager().addSprite(fruit);
    }
    
}
