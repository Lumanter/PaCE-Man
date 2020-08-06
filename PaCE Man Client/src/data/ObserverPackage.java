package data;

import java.util.ArrayList;
import sprites.Dot;
import sprites.Fruit;
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
    
    // game's score
    public Integer score  = 0;
    
    // game's dots
    public ArrayList<Dot> dots = new ArrayList<>();
    
    // game lives
    public Integer lives = 3;
    
    // indicates the current game state
    public Integer gameState = 0;
    
    // game's fruits
    public ArrayList<Fruit> fruits = new ArrayList<>();
}
