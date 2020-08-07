package windows;

import clientViews.PlayerView;
import java.awt.EventQueue;
import javax.swing.JFrame;
import data.Constants;
import mainProcess.ClientPlayer;

/**
 * Window of the player mode
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class PlayerWindow extends JFrame {
    
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
        
        ClientPlayer clientProcess = new ClientPlayer(serverIp, Constants.SERVERPORT, view);
        
        this.add(view);
    }
    
}
