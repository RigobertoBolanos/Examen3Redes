package connection;
/**
 * Es el hilo encargado de por cada cliente que acceda a localhost:port , generarle
 * un hiloClientHandler propio para su posterior manejo
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloDespliegueAppWebLogin extends Thread {
	
	Server server;
	
	public HiloDespliegueAppWebLogin(Server server) {
		
		this.server = server;
	}
	
	public void run() {
		
		while(server.isWebService()) {
			System.out.println(":::Web Server Started:::");
			ServerSocket serverSocket = server.getServerSocketWebLogin();
			try {
				Socket cliente = serverSocket.accept();
				System.out.println("Lee");
				HiloClientHandlerLogin hilo = new HiloClientHandlerLogin(cliente, server);
				hilo.start();	
				
			} catch (IOException e) {
				System.out.println("Exception in HiloDespliegueAppWeb");
			}
			
		}
		
	}
	

}
