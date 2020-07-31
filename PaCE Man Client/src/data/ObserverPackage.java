package data;

import java.util.ArrayList;
import sprites.Ghost;
import sprites.Pacman;
import sprites.Pill;

/**
 * Simple container class to hold the data needed by the 
 * observer view to display the game state
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class ObserverPackage {
    
    // game level
    public Integer level = 1;
    
    // indicates if a pill is active
    public Boolean pillActive = false;
    
    // game's pacman
    public Pacman pacman = new Pacman();
    
    // game's ghosts
    public ArrayList<Ghost> ghosts = new ArrayList<>();
    
    // game's pills
    public ArrayList<Pill> pills = new ArrayList<>();
}
