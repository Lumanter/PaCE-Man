package connector;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainProcess.ClientObserver;

import mainProcess.ClientPlayer;

/**
 * Class that manages socket connection
 * @author Jonathan Gonzalez
 */
public class ConnectionManager implements Runnable{
    private Socket socket = null;
    private DataInputStream input = null;
    private PrintWriter output = null;
    private String address = "";
    private Integer port = 0;
    private ClientPlayer clientPlayer = null;
    private ClientObserver clientObserver = null;
    
    public ConnectionManager(String address, Integer port, ClientPlayer clientProcess) {
        
        this.clientPlayer = clientProcess;
        this.address = address;
        this.port = port;
    }
    
    public ConnectionManager(String address, Integer port, ClientObserver clientProcess) {
        
        this.clientObserver = clientProcess;
        this.address = address;
        this.port = port;
    }
    
    @Override
    public void run(){
        try {
            this.startSocketConnection();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Starts the connection between the current thread and the server
     * @throws IOException 
     */
    public void startSocketConnection() throws IOException{
        
        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(address, port), 1000*2);
            System.out.println("Connected to Server in address:port " + address + ":" + port.toString());

            input = new DataInputStream(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            
            }catch(IOException ex){
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        if(clientPlayer != null){
        
            while(!this.clientPlayer.isFinished){



                clientPlayer.updateInputBuffer();

                if(!this.clientPlayer.inputBuffer.equals("")){
                    output.println(this.clientPlayer.inputBuffer);
                    System.out.println("Sending: " + this.clientPlayer.inputBuffer);
                    try {
                        this.clientPlayer.outputBuffer = input.readLine();
                        if(!this.clientPlayer.outputBuffer.equals("")){
                            this.clientPlayer.processOutput();
                            //System.out.println("Out now is \n" + this.clientPlayer.outputBuffer);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    this.clientPlayer.inputBuffer = "";
                }
            }
        }else{
            while(!this.clientObserver.isFinished){



                clientObserver.inputBuffer = "observer,";

                if(!this.clientObserver.inputBuffer.equals("")){
                    output.println(this.clientObserver.inputBuffer);

                    try {
                        this.clientObserver.outputBuffer = input.readLine();
                        this.clientObserver.messageToObserverPackage();
                        System.out.println("Out now is \n" + this.clientObserver.outputBuffer);
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    this.clientObserver.inputBuffer = "";
                }
            }
        }
        
        socket.close();
    }
}
    
