package windows;

import clientViews.ObserverView;
import data.Constants;
import java.awt.EventQueue;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import mainProcess.ClientObserver;

/**
 * Window of the observer mode
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class ObserverWindow extends JFrame {
    
    // observer view
    private ObserverView view;
    
    /**
     * Constructor passes the server ip to connect
     * @param serverIp server ip address
     */
    public ObserverWindow(String serverIp) {
        this.setTitle("PaCE Man Observer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.LEVEL_SIZE, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.view = new ObserverView();
        
        ClientObserver clientProcess = new ClientObserver(serverIp, Constants.SERVERPORT, view);
        this.add(view);
    }
    
    public ObserverView getView() {
        return view;
    }
}
