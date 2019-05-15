package Puntaje;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import connection.Server;
import model.Player;

public class ScoreThread extends Thread{
	
	private Server server;
	
	public ScoreThread(Server server)
	{
		this.server = server;
	}
	
	@Override
	public void run() 
	{
		super.run();
		while(server.getGame().isActive())
		{
			
		}
		try {
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
				br.write(nick + "," + score + "," + date + ",");
				for (Player adversario : server.getGame().getPlayers()) 
				{
					if(!player.getNickName().equals(nick))	
					{
						br.write(adversario.getNickName() + "/");
					}
				}
				br.write("," + gano + "\n");
			}
			br.flush();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
