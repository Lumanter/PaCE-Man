package clientViews;

import data.Constants;
import data.GameState;
import data.ObserverPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import sprites.Dot;
import sprites.Fruit;
import sprites.Ghost;
import sprites.Level;
import sprites.Pacman;
import sprites.Pill;


/**
 * Observer mode view. Handles the display updates
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class ObserverView extends JPanel {
    
    // game level number
    private Integer levelNumber;
    
    // game level
    private Level level;
    
    // game pacman
    private Pacman pacman;
    
    // game ghosts
    private ArrayList<Ghost> ghosts;
    
    // game pills
    private ArrayList<Pill> pills;
    
    // game score
    private Integer score;
    
    // game dots
    private ArrayList<Dot> dots;
    
    // game lives
    private Integer lives;
    
    // current game state
    private GameState gameState;
    
    // game fruits
    private ArrayList<Fruit> fruits;
    
    /**
     * Constructor sets the default display data
     */
    public ObserverView() {
        ObserverPackage defaultData = new ObserverPackage();
        levelNumber = defaultData.level;
        level = new Level(defaultData.level);
        pacman = defaultData.pacman;
        ghosts = defaultData.ghosts;
        pills = defaultData.pills;
        score = defaultData.score;
        dots = defaultData.dots;
        lives = defaultData.lives;
        fruits = defaultData.fruits;
        gameState = decodeGameState(defaultData.gameState);
        this.setBackground(Color.black);
    }
    
    /**
     * Updates the display with a given data package
     * @param updatedData data package
     */
    public void update(ObserverPackage updatedData) {
        // update the display variables
        level = new Level(updatedData.level);
        pacman = updatedData.pacman;
        ghosts = updatedData.ghosts;
        pills = updatedData.pills;
        
        if (updatedData.pillActive)
            for (Ghost ghost: ghosts)
                ghost.setIsEdible(true);
   
        score = updatedData.score;
        
        dots = updatedData.dots;
        
        lives = updatedData.lives;
        
        gameState = decodeGameState(updatedData.gameState);
        
        fruits = updatedData.fruits;
        
        this.repaint();
    }
    
    /**
     * Refreshes the display panel
     * @param graphics render tool
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D renderer = (Graphics2D) graphics;  
        
        // render level
        level.render(renderer);
        
        // render dots
        for (Dot dot : dots)
            dot.render(renderer, this);
        
        // render pills
        for (Pill pill : pills)
            pill.render(renderer, null);
        
        for (Fruit fruit : fruits) 
            fruit.render(renderer, this);
        
        // render ghosts
        for (Ghost ghost: ghosts)
                ghost.render(renderer, this);
        
        renderer.setColor(Color.white);
        
        // render level number
        renderer.drawString("Level " + String.valueOf(levelNumber), (int)(Constants.LEVEL_SIZE*0.15), 445);
        
        // render lives
        renderer.drawString("Lives: " + String.valueOf(lives), (int)(Constants.LEVEL_SIZE*0.4), 445);
        
        // render score
        renderer.drawString("Score: " + String.valueOf(score), (int)(Constants.LEVEL_SIZE*0.65), 445);
        
        // render pacman
        pacman.render(renderer, this);
        
        if (gameState != GameState.ACTIVE)
            renderStateMessage(renderer, gameState);
    }
    
    /**
     * Decodes a game state from integer to its enumeration representation
     * @param codedState game state integer representation
     * @return game state enumeration representation
     */
    private GameState decodeGameState(Integer codedState) {
        switch (codedState) {
            case 1:
                return GameState.WIN;
            case -1:
                return GameState.OVER;
            default:
                return GameState.ACTIVE;
        }
    }
    
    /**
     * Shows win or game over message
     * @param renderer render tool
     * @param gameState current game state
     */
    private void renderStateMessage(Graphics2D renderer, GameState gameState) {
        renderer.setColor(Color.yellow);
        renderer.fillRect(100, 100, 221, 220);
        
        String message;
        if (gameState == GameState.WIN)
            message = "YOU WIN!";
        else 
            message = "GAME OVER";
        
        renderer.setColor(Color.black);
        renderer.drawString(message, 180, 150);
        
        renderer.drawString("Your score: " + String.valueOf(score), 170, 200);
        
        renderer.drawString("Press SPACE to play again", 140, 240);
    }
}
