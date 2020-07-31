package clientViews;

import commands.Command;
import commands.CreateFruitCommand;
import data.Direction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import data.Constants;
import data.ObserverPackage;
import data.Position;
import java.util.ArrayList;
import sprites.FruitManager;
import sprites.Level;
import sprites.Pacman;
import sprites.Ghost;
import sprites.PillManager;

/**
 * Panel that manages the player view 
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class PlayerView extends JPanel implements ActionListener {
    
    // game level number
    private Integer levelNumber;
    
    // game pacman
    private Pacman pacman;
    
    // game ghosts
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    
    // game level
    private Level level;

    // game pill manager
    private PillManager pillManager;
    
    // game fruit manager
    private FruitManager fruitManager;
            
    /**
     * Constructor initializes the needed variables and panel configurations
     */
    public PlayerView() {
        startGameState();
        setupDirectionKeyListener();
        configurePanel();
    }
    
    /**
     * Starts the variables that define the game state
     */
    private void startGameState() {
        this.levelNumber = 1;
        this.pacman = new Pacman();
        this.level = new Level(levelNumber);
        this.pillManager = new PillManager();
        this.fruitManager = new FruitManager();
        
        (new CreateFruitCommand(this, 10, 20, 20)).execute();
        (new CreateFruitCommand(this, 10, 180, 200)).execute();
    }
 
    /**
     * Sets up the input key listener to change pacman direction
     */
    private void setupDirectionKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                
                // update pacman desired direction on arrow key input
                Integer key = e.getKeyCode();
                Direction desiredDirection = null;
                switch(key){
                    case KeyEvent.VK_RIGHT:
                        desiredDirection = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_LEFT:
                        desiredDirection = Direction.LEFT;
                        break;  
                    case KeyEvent.VK_UP:
                        desiredDirection = Direction.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        desiredDirection = Direction.DOWN;
                }
                
                // set desired direction just if the direction is not the same as the current direction
                Boolean desiredIsNotCurrentDirection = (desiredDirection != null && desiredDirection != pacman.getCurrentDirection());
                if (desiredIsNotCurrentDirection)
                    pacman.setNextDirection(desiredDirection);
            }
        });
    }
    
    /**
     * Sets panel refresh timer and background color
     */
    private void configurePanel() {
        // call this class' actionPerformed to refresh display
        Timer timer = new Timer(Constants.FRAME_DELAY, this);
        timer.start();
        
        this.setFocusable(true);
        this.setBackground(Color.black);
    }

    /**
     * Refreshes the display panel
     * @param renderer renderer tool
     */
    @Override
    protected void paintComponent(Graphics renderer) {
        super.paintComponent(renderer);
        moveSprites();
        checkCollisions();
        renderSprites(renderer);
    }
    
    /**
     * Moves the dynamic sprites (ghosts and pacman)
     */
    private void moveSprites() {
        // move ghosts
        for(Ghost ghost: ghosts)
                ghost.move();
        
        // move pacman
        this.pacman.warpIfNecessary();
        this.pacman.move(level);
    }
    
    /**
     * Check for game collisions and handles the consequent events
     */
    private void checkCollisions() {
        // check for pills eaten
        Boolean pillEaten = (pillManager.hasCollision(pacman) != null);
        if (pillEaten) {
            Position eatenPillPosition = pillManager.hasCollision(pacman);
            pillManager.removePill(eatenPillPosition);
            pillManager.setPillActive(true);
            for (Ghost ghost: ghosts)
                    ghost.setIsEdible(true);
        }
        
        // check for fruits eaten
        Boolean fruitEaten = (fruitManager.hasCollision(pacman) != null);
        if (fruitEaten) {
            Position eatenFruitPosition = fruitManager.hasCollision(pacman);
            fruitManager.removeFruit(eatenFruitPosition);
        }
        
        // ghosts collisions
        for (Integer i = 0; i < ghosts.size(); i++) {
            Ghost ghost = ghosts.get(i);    
            if (ghost.collides(pacman)) {
                if (ghost.isIsEdible())
                    // ghost eaten
                    ghosts.remove(i);
                else
                    // pacman life -1
                    pacman.resetPosition();
            }
        }
    }
    
    /**
     * Renders all the sprites to the display
     * @param graphics render tool
     */
    private void renderSprites(Graphics graphics) { 
        Graphics2D renderer = (Graphics2D) graphics;   
 
        // render level background
        this.level.render(renderer); 
        
        // render power pills
        this.pillManager.render(renderer);
        
        // render fruits
        this.fruitManager.render(renderer);
        
        // render ghosts
        for(Ghost ghost: ghosts) 
                ghost.render(renderer, this);
        
        // render pacman
        this.pacman.render(renderer, this);
                    
        // update display and release renderer resources
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * The action listener is call each frame to handle frame-by-frame
     * updates and refresh the display
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // animate Pacman
        pacman.increaseAnimationTimer();
        pacman.updateAnimation();
        
        // decrease pill timer if there's a pill active
        if (pillManager.isPillActive()) {
            
            Boolean pillTimeFinished = (pillManager.tickTimer() == 0);
            if (pillTimeFinished) 
                // pill time finished so ghosts are no longer edible
                for(Ghost ghost: ghosts)
                        ghost.setIsEdible(false);
            
        }
        
        // update display on any action performed
        this.repaint();
    }
    
    /**
     * Returns the game state as a package to display in the observer view
     * 
     * @return game state as a package to display in the observer view
     */
    public ObserverPackage getObserverPackage() {
        ObserverPackage data = new ObserverPackage();
        data.pacman = this.pacman;
        data.ghosts = this.ghosts;
        data.pills = this.pillManager.getPills();
        
        // if there're ghosts, a pill is active 
        // if the first is in edible mode, then all are
        if (!ghosts.isEmpty())
            data.pillActive = ghosts.get(0).isIsEdible();
        
        return data;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public Level getLevel() {
        return level;
    }

    public PillManager getPillManager() {
        return pillManager;
    }

    public FruitManager getFruitManager() {
        return fruitManager;
    }
}
