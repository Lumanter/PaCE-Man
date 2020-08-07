package windows;

import clientViews.PlayerView;
import fakeServers.FakePlayerServer;
import java.awt.EventQueue;
import javax.swing.JFrame;
import data.Constants;

/**
 * Window of the player mode
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class PlayerWindow extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new PlayerWindow("");
            ex.setVisible(true);
        });
    }
    
    /**
     * Constructor passes the server ip to connect
     * @param serverIp server ip address
     */
    public PlayerWindow(String serverIp) {
        this.setTitle("PaCE Man Player");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.LEVEL_SIZE, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        PlayerView view = new PlayerView();
        
        //FakePlayerServer fakeServer = new FakePlayerServer(view);
        //fakeServer.setVisible(true);
        
        this.add(view);
    }
    
}
