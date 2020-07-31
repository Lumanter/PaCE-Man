package app;

import data.ObserverPackage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import sprites.Ghost;
import sprites.Level;
import sprites.Pacman;
import sprites.Pill;


public class ObserverView extends JPanel implements ActionListener {
    
    private Level level;
    private Pacman pacman;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Pill> pills;
    
    public ObserverView() {
        ObserverPackage defaultData = new ObserverPackage();
        level = new Level(defaultData.level);
        pacman = defaultData.pacman;
        ghosts = defaultData.ghosts;
        pills = defaultData.pills;
        this.setBackground(Color.black);
    }
    
    public void update(ObserverPackage updatedData) {
        // update the display variables
        level = new Level(updatedData.level);
        pacman = updatedData.pacman;
        ghosts = updatedData.ghosts;
        pills = updatedData.pills;
        this.repaint();
    }
    
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // refresh display
        this.repaint();
    }
}
