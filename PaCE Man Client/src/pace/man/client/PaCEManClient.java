
package pace.man.client;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class PaCEManClient extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new PaCEManClient();
            ex.setVisible(true);
        });
    }
    
    public PaCEManClient() {
        initUI();
    }
    
    private void initUI() {
        this.setTitle("PaCE Man");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 645, 720
        this.setSize(436, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        GameBoard gameBoard = new GameBoard();
        this.add(gameBoard);
        
    }
    
}
