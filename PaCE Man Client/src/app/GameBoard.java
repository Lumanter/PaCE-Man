
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
import sprites.Level;
import sprites.Pacman;
import sprites.Ghost;


public class GameBoard extends JPanel implements ActionListener {
    
    private int levelNumber;
    
    private Pacman pacman;
    private Level level;
    
    private Ghost[] ghosts;
            
    public GameBoard() {
        initVariables();
        initBoard();
    }
    
    private void initVariables() {
        this.levelNumber = 1;
        this.pacman = new Pacman();
        this.level = new Level(levelNumber);
        
        this.ghosts = new Ghost[Constants.GHOSTS_NUMBER];
        this.ghosts[GhostColor.RED] = new Ghost(levelNumber, GhostColor.RED);
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
        
        renderSprites(renderer);
    }
    
    private void moveSprites() {
        
        for(Ghost ghost: ghosts) {
            if (ghost != null)
                ghost.move();
        }
        
        // move pacman
        this.pacman.warpIfNecessary();
        this.pacman.move(level);
    }
    
    private void renderSprites(Graphics graphics) { 
        Graphics2D renderer = (Graphics2D) graphics;   
 
        for(Ghost ghost: ghosts) {
            if (ghost != null)
                ghost.render(renderer, this);
        }
        
        //this.redGhost.render(renderer, this);
        
        this.pacman.render(renderer, this);
        
        // render level background
        this.level.render(renderer);             
        
        // update display and release renderer resources
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // animate Pacman
        this.pacman.increaseAnimationTimer();
        this.pacman.updateAnimation();
        
        // update display on any action performed
        this.repaint();
    }
    
}
