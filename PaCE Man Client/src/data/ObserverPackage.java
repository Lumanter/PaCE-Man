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
    public Integer level = 1;
    public Pacman pacman = new Pacman();
    public ArrayList<Ghost> ghosts = new ArrayList<>();
    public ArrayList<Pill> pills = new ArrayList<>();
}
