/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProcess;

import clientViews.ObserverView;
import connector.ConnectionManager;
import data.ObserverPackage;
import data.Position;
import java.util.ArrayList;
import sprites.Ghost;
import sprites.Pacman;

/**
 *
 * @author Jon Gs
 */
public class ClientObserver {
    public String inputBuffer = "";
    public String outputBuffer = "";
    public Boolean isFinished = false;
    private ObserverView view = null;
    private ConnectionManager connector = null;
    
    public ClientObserver(String serverIP, int serverPort, ObserverView view){
        this.view = view;
        this.connector = new ConnectionManager(serverIP, serverPort, this);
        
        Thread connectorThread = new Thread(this.connector);
        connectorThread.start();
    }
    
    public void messageToObserverPackage(){
        String[] parsedMessage = outputBuffer.split(",");
        
        ObserverPackage observerPackage = new ObserverPackage();
        
        observerPackage.level = 1;
        
        Pacman pacman = new Pacman(Integer.parseInt(parsedMessage[1]),
                Integer.parseInt(parsedMessage[2]),
                Integer.parseInt(parsedMessage[3]));
        
        observerPackage.pacman = pacman;
        
        observerPackage.lives = Integer.parseInt(parsedMessage[4]);
        
        for (int i = 0; i < 4; i++) {
            int ghost_x = Integer.parseInt(parsedMessage[5 + 4*i]);
            if(ghost_x != -1){
                int ghost_y = Integer.parseInt(parsedMessage[6 + 4*i]);
                int ghost_color = Integer.parseInt(parsedMessage[8 + 4*i]);
                Ghost current = new Ghost(ghost_color, ghost_x, ghost_y);
                observerPackage.ghosts.add(current);
            }
            
        }
        
        view.update(observerPackage);
        
    }
    
}