package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import connection.Server;
import model.Game;
import model.Player;

public class GameThread extends Thread{
	
	private int countDown;
	private int limit;
	private Server server;
	
	public GameThread(Server server,int limit) {
		this.server=server;
		this.limit=limit;
		this.countDown=limit;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Game game=server.getGame();
			while(game.isActive()) {
				
				if(game.onePlayer()) {
					System.out.println("Se acabó el juego");
					game.setActive(false);
					BufferedWriter br = new BufferedWriter(new FileWriter(server.SCORE_PATH, true));
					for (Player player : server.getGame().getPlayers()) 
					{
						String nick = player.getNickName();
						double score = player.getBall().getRadius();
						GregorianCalendar calendario = new GregorianCalendar();
						Date date = calendario.getTime();
						boolean gano = false;
						if(server.getGame().getWinner().getNickName().equals(nick))
						{
							gano = true;
						}
						br.write(nick + "&" + score + "&" + date + "&");
						System.out.println("Se registró puntaje de: " + nick);
						if(server.getGame().getPlayers().size() != 1)
						{
							for (Player adversario : server.getGame().getPlayers()) 
							{
								if(!player.getNickName().equals(nick))	
								{
									br.write(adversario.getNickName() + "/");
								}
							}
						}
						else
						{
							br.write("No hubo adversarios");
						}
						
						br.write("&" + gano + "#");
					}
					br.flush();
					br.close();
				}	
				
				if(countDown>0) {
					Thread.sleep(1000);
					countDown--;
				}
				else {
					game.setActive(false);
				}
			}
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
