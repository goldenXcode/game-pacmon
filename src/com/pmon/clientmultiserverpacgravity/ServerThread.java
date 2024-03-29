package com.pmon.clientmultiserverpacgravity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates the thread for sending, receiving, autodiscovery, connectioninfo
 */
public class ServerThread extends Thread{
 
    private CMGameEngine ges;
    private ServerSending sendThread;
    private ServerReceiving receiveThread;
    private ServerConnectionInfo dispatchReceiver;
    private ServerAutoDiscovery serverDiscovery;
    private AtomicBoolean clientReady;
    
    private boolean isThreadDead=false;

    public ServerThread(CMGameEngine game, AtomicBoolean ready)
    {
//    	 ges=game;
//    	 clientReady=ready;
//    	 
//         //we want to create receiveThread so incase client sends data
//         receiveThread=new ServerReceiving(ges,  9876);
//         sendThread = new ServerSending();
//         
//         //run the server discover
//         serverDiscovery=new ServerAutoDiscovery();
//         //dispatcher, listens and sends the info of receivingServer to client
//         dispatchReceiver=new ServerConnectionInfo();
    }
    
//    public void killSendingReceiving()
//    {	//need to sleep so we can send status to client
//    	try {
//			this.sleep(300);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    	
//    	isThreadDead=true;
// 
//    	sendThread.isRunning.set(false);
//    	sendThread.DestroySocket();
//     	receiveThread.isRunning.set(false);
//    	
//    	serverDiscovery.DestroySocket();
//    	dispatchReceiver.DestroySocket();
//    	receiveThread.DestroySocket();
//    	   
//    }
//    
//    public void run()
//    {
//         serverDiscovery.start();
//         dispatchReceiver.runReceivingDispather();
//         
//         //returns port of client,
//         int first=dispatchReceiver.firstPlayerData;
//         
//         
//         //port of client, ipaddress
//         sendThread=new ServerSending(first, dispatchReceiver.firstIPAddress, ges);
//         
//         //sets the ipaddress of client
//         //remember they shared the variable firstIPAddress, secondIPAddress
//         receiveThread.setPlayer(dispatchReceiver.firstIPAddress);
//        
//         //start receive server
//         receiveThread.start();
//
//         //waiting for client players to be ready before starting game engine
//         while(!receiveThread.ready )
//         {  	
//         	try {
// 				Thread.sleep(10);
// 			} catch (InterruptedException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
//         	
//         	if(isThreadDead)
//         		return;
//         }
//         
//         if(receiveThread.ready)
//         {
//        	 //this variable is used for CMGameActivity, to dismiss progress dialog
//        	 this.clientReady.set(true);
//         
//         	//put a sleep here because clientReady dialog box will show for 1500ms
//         	//so we don't want to start gameEngine right away
//         	try {
//			Thread.sleep(2000);
//         	} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			}
//        
//         	//remem gameEngine has its own thread running
//            ges.startTheEngine();
//            sendThread.start();
//         
//         }
//
//         //wait for thread to die
////         try {
////             sendThread.join();
////             receiveThread.join();
////             
////         } catch (InterruptedException ex) {
////             Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
////             System.out.println("FINISHSHFSDFSLKDGJE");
////         }
//    }
}
