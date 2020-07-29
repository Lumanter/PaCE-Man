
package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import data.Constants;


public class PlayerWindow extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new PlayerWindow();
            ex.setVisible(true);
        });
    }
    
    public PlayerWindow() {
        setupFrame();
    }
    
    private void setupFrame() {
        this.setTitle("PaCE Man Player");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.LEVEL_SIZE, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        PlayerView playerView = new PlayerView();
        
        FakePlayerServer fakeServer = new FakePlayerServer(playerView);
        fakeServer.setVisible(true);
        
        this.add(playerView);
    }
    
}
