package windows;

import clientViews.ObserverView;
import fakeServers.FakeObserverServer;
import data.Constants;
import java.awt.EventQueue;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ObserverWindow extends JFrame {
    
    private ObserverView view;
    
    public ObserverWindow() {
        setupFrame();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new ObserverWindow();
            ex.setVisible(true);
        });
    }
    
    private void setupFrame() {
        this.setTitle("PaCE Man Observer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.LEVEL_SIZE, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.view = new ObserverView();
        
        /**FakeObserverServer fakeServer = new FakeObserverServer(view);
        fakeServer.setVisible(true);**/
        this.add(view);
    }
    
    public ObserverView getView() {
        return view;
    }
}
