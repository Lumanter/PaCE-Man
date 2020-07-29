
package app;

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
import data.GhostColor;
import data.Position;
import java.util.ArrayList;
import sprites.Level;
import sprites.Pacman;
import sprites.Ghost;
import sprites.PillManager;


public class PlayerView extends JPanel implements ActionListener {
    
    private int levelNumber;
    
    private Pacman pacman;
    private Level level;
    
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    
    
    private PillManager pillManager;
            
    public PlayerView() {
        initVariables();
        initBoard();
    }
    
    private void initVariables() {
        this.levelNumber = 1;
        this.pacman = new Pacman();
        this.level = new Level(levelNumber);
        
        this.ghosts.add(new Ghost(levelNumber, GhostColor.RED));
        
        this.pillManager = new PillManager();
        pillManager.addPill(20*1, 20*1);
        pillManager.addPill(180, 300);
    }
 
    
    private void initBoard() {
        // call this class' actionPerformed to refresh display
        Timer timer = new Timer(Constants.FRAME_DELAY, this);
        timer.start();
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                
                // update pacman desired direction on arrow key input
                int key = e.getKeyCode();
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
                if (desiredDirection != null && desiredDirection != pacman.getCurrentDirection())
                    pacman.setNextDirection(desiredDirection);
            }
        });
        
        this.setFocusable(true);
        this.setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics renderer) {
        super.paintComponent(renderer);
        moveSprites();
        checkCollisions();
        renderSprites(renderer);
    }
    
    private void moveSprites() {
        
        // move ghosts
        for(Ghost ghost: ghosts) {
            if (ghost != null)
                ghost.move();
        }
        
        // move pacman
        this.pacman.warpIfNecessary();
        this.pacman.move(level);
    }
    
    private void checkCollisions() {
        
        // check for pills eaten
        boolean pillEaten = (pillManager.hasCollision(pacman) != null);
        if (pillEaten) {
            Position eatenPillPosition = pillManager.hasCollision(pacman);
            pillManager.removePill(eatenPillPosition);
            pillManager.setPillActive(true);
            for (Ghost ghost: ghosts)
                    ghost.setIsEdible(true);
        }
        
        // ghosts collisions
        for (int i = 0; i < ghosts.size(); i++) {
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
    
    private void renderSprites(Graphics graphics) { 
        Graphics2D renderer = (Graphics2D) graphics;   
 
        // render level background
        this.level.render(renderer); 
        
        // render ghosts
        for(Ghost ghost: ghosts) {
                ghost.render(renderer, this);
        }
        
        // render pacman
        this.pacman.render(renderer, this);
                    
        // render power pills
        this.pillManager.render(renderer);
        
        // update display and release renderer resources
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // animate Pacman
        pacman.increaseAnimationTimer();
        pacman.updateAnimation();
        
        // decrease pill timer if there's a pill active
        if (pillManager.isPillActive()) {
            
            boolean pillTimeFinished = (pillManager.tickTimer() == 0);
            if (pillTimeFinished) {
                // pill time finished so ghosts are no longer edible
                for(Ghost ghost: ghosts)
                        ghost.setIsEdible(false);
            }
        }
        
        // update display on any action performed
        this.repaint();
    }
    
}
