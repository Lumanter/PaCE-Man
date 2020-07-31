package app;

import data.Constants;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ObserverWindow extends JFrame {
    
    public ObserverWindow() {
        setupFrame();
    }
    
    private void setupFrame() {
        this.setTitle("PaCE Man Observer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Constants.LEVEL_SIZE, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        ObserverView view = new ObserverView();
        
        FakeObserverServer fakeServer = new FakeObserverServer(view);
        fakeServer.setVisible(true);
        
        
        this.add(view);
    }
    
}
