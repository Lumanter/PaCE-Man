package clientViews;

import commands.IncrementScoreCommand;
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
import data.GameDatabase;
import data.GameState;
import data.ObserverPackage;
import data.Position;
import java.util.ArrayList;
import sprites.Dot;
import sprites.Fruit;
import sprites.Level;
import sprites.Pacman;
import sprites.Ghost;
import sprites.PillManager;
import sprites.SpriteManager;

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
    private ArrayList<Ghost> ghosts;
    
    // game level
    private Level level;

    // game pill manager
    private PillManager pillManager;
    
    // game fruit manager
    private SpriteManager fruitManager;
        
    // level dots manager
    private SpriteManager dotsManager;
    
    // pacman lifes
    private Integer lifes;
    
    // game score
    private Integer score;
    
    // game state
    private GameState gameState;
    
    /**
     * Constructor initializes the needed variables and panel configurations
     */
    public PlayerView() {
        restartGame();
        loadLevel(3);
        setupDirectionKeyListener();
        configurePanel();
    }
    
    /**
     * Starts the variables that define the game state
     */
    private void restartGame() {
        this.lifes = Constants.DEFAULT_LIFES;
        this.score = 0;
        this.gameState = GameState.ACTIVE;
        loadLevel(1); 
    }
 
    /**
     * Sets up the input key listener to change pacman direction
     */
    private void setupDirectionKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                
                
                Integer key = e.getKeyCode();
                
                if (gameState == GameState.ACTIVE) {
                    // update pacman desired direction on arrow key input
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
                } else {
                    
                    // on win or game over state
                    if (key == KeyEvent.VK_SPACE)
                        restartGame(); 
                }
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
        
        if (gameState == GameState.ACTIVE) {
            moveSprites();
            checkCollisions();
        }
            
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
            (new IncrementScoreCommand(this, Constants.PILL_POINTS)).execute();
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
            
            Fruit fruit = (Fruit) fruitManager.getSprite(eatenFruitPosition);
            score += fruit.getPoints();            
            
            fruitManager.removeSprite(eatenFruitPosition);
        }
        
        // check for dots eaten
        Boolean dotEaten = (dotsManager.hasCollision(pacman) != null);
        if (dotEaten) {
            (new IncrementScoreCommand(this, Constants.DOT_POINTS)).execute();
            Position eatenDotPosition = dotsManager.hasCollision(pacman);
            dotsManager.removeSprite(eatenDotPosition);
            
            Boolean levelFinished = dotsManager.getSprites().isEmpty();
            if (levelFinished) {
                if (this.levelNumber == 3)
                    gameState = GameState.WIN;
                else
                    loadLevel(levelNumber + 1);
            }
        }
        
        // ghosts collisions
        for (int i = 0; i < ghosts.size(); i++) {
            Ghost ghost = ghosts.get(i);  
            if (ghost.collides(pacman)) {
                
                if (ghost.isIsEdible()) {
                    // ghost eaten
                    (new IncrementScoreCommand(this, Constants.GHOST_POINTS)).execute();
                    ghosts.remove(i);
                    
                } else {
                    // pacman life -1
                    --lifes;
                    pacman.resetPosition();
                    // game over
                    if (lifes < 0) {
                        lifes = 0;
                        this.gameState = GameState.OVER;
                    }
                        
                }
                
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
        
        // render dots
        this.dotsManager.render(renderer);
        
        // render power pills
        this.pillManager.render(renderer);
        
        // render fruits
        this.fruitManager.render(renderer);
        
        // render ghosts
        for(Ghost ghost: ghosts) 
                ghost.render(renderer, this);
        
        // render pacman
        this.pacman.render(renderer, this);
        
        renderer.drawString("Level " + String.valueOf(levelNumber), (int)(Constants.LEVEL_SIZE*0.15), 445);
        renderer.drawString("Lifes: " + String.valueOf(lifes), (int)(Constants.LEVEL_SIZE*0.4), 445);
        renderer.drawString("Score: " + String.valueOf(score), (int)(Constants.LEVEL_SIZE*0.65), 445);
        
        if (gameState != GameState.ACTIVE)
            renderStateMessage(renderer, gameState);
        
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
        
        data.level = this.levelNumber;
        
        // if there're ghosts, a pill is active 
        // if the first is in edible mode, then all are
        if (!ghosts.isEmpty())
            data.pillActive = ghosts.get(0).isIsEdible();
        
        data.pacman = this.pacman;
        
        data.ghosts = this.ghosts;
        
        data.pills = this.pillManager.getPills();
        
        data.score = this.score;
        
        for (int i = 0; i < dotsManager.getSprites().size(); i++) {
            Dot dot = (Dot) dotsManager.getSprites().get(i);
            data.dots.add(dot);
        }
        
        data.lifes = this.lifes;
        
        if (gameState == GameState.OVER)
            data.gameState = -1;
        if (gameState == GameState.WIN)
            data.gameState = 1;
        
        return data;
    }
    
    /**
     * Increment the game score by a given increment
     * 
     * @param increment given increment
     */
    public void incrementScore(Integer increment) {
       this.score += increment; 
    }

    /**
     * Loads the game to a given level
     * 
     * @param level given level
     */
    public void loadLevel(Integer level) {
        GameDatabase database = GameDatabase.getInstance();
        
        this.levelNumber = level;
        this.pacman = new Pacman();
        this.level = new Level(levelNumber);
        this.pillManager = new PillManager();
        this.fruitManager = new SpriteManager();
        this.ghosts = new ArrayList<>();
        
        this.dotsManager = new SpriteManager();
        this.dotsManager.setSprites(database.getDots(levelNumber));
    }
    
    /**
     * Shows win or game over message
     * @param renderer render tool
     * @param gameState current game state
     */
    private void renderStateMessage(Graphics2D renderer, GameState gameState) {
        renderer.setColor(Color.white);
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

    public SpriteManager getFruitManager() {
        return fruitManager;
    }
    
}
