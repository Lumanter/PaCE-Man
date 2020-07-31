package clientViews;

import data.ObserverPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import sprites.Ghost;
import sprites.Level;
import sprites.Pacman;
import sprites.Pill;


public class ObserverView extends JPanel {
    
    // game level
    private Level level;
    
    // game pacman
    private Pacman pacman;
    
    // game ghosts
    private ArrayList<Ghost> ghosts;
    
    // game pills
    private ArrayList<Pill> pills;
    
    /**
     * Constructor sets the default display data
     */
    public ObserverView() {
        ObserverPackage defaultData = new ObserverPackage();
        level = new Level(defaultData.level);
        pacman = defaultData.pacman;
        ghosts = defaultData.ghosts;
        pills = defaultData.pills;
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
        
        // render pills
        for(Pill pill : pills)
            pill.render(renderer, null);
        
        // render ghosts
        for(Ghost ghost: ghosts)
                ghost.render(renderer, this);
        
        // render pacman
        pacman.render(renderer, this);
    }
}
