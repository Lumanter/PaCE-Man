
package main;

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
import sprites.Level;
import sprites.Pacman;


public class GameBoard extends JPanel implements ActionListener {
    
    private final int FRAME_DELAY = 40; 
    
    private Pacman pacman;
    private Level level;
    
    public GameBoard() {
        initVariables();
        initBoard();
    }
    
    private void initVariables() {
        this.pacman = new Pacman(200, 300);
        this.level = new Level(1);
    }
 
    
    private void initBoard() {
        // call this class' actionPerformed to refresh display
        Timer timer = new Timer(FRAME_DELAY, this);
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
        // move pacman
        this.pacman.warpIfNecessary();
        this.pacman.move(level);
    }
    
    private void renderSprites(Graphics graphics) { 
        Graphics2D renderer = (Graphics2D) graphics;   
 
        this.pacman.render(renderer, this);
        
        // render level background
        this.level.render(renderer);             
        
        // update display and release renderer resources
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // animate Pacman
        this.pacman.increaseAnimationTimer(FRAME_DELAY);
        this.pacman.updateAnimation();
        
        // update display on any action performed
        this.repaint();
    }
    
}
