package clientViews;

import data.Constants;
import data.ObserverPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import sprites.Dot;
import sprites.Ghost;
import sprites.Level;
import sprites.Pacman;
import sprites.Pill;


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
    
    // game lifes
    private Integer lifes;
    
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
        lifes = defaultData.lifes;
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
        
        lifes = updatedData.lifes;
        
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
        
        // render ghosts
        for (Ghost ghost: ghosts)
                ghost.render(renderer, this);
        
        renderer.setColor(Color.white);
        
        // render level number
        renderer.drawString("Level " + String.valueOf(levelNumber), (int)(Constants.LEVEL_SIZE*0.15), 445);
        
        // render lifes
        renderer.drawString("Lifes: " + String.valueOf(lifes), (int)(Constants.LEVEL_SIZE*0.4), 445);
        
        // render score
        renderer.drawString("Score: " + String.valueOf(score), (int)(Constants.LEVEL_SIZE*0.65), 445);
        
        // render pacman
        pacman.render(renderer, this);
    }
}
