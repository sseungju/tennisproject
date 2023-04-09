package JavaTenisProject;
import java.util.ArrayList;
import java.util.Random;

public class Tenis extends GameMethod {

public static void main(String[] args) {
		
		GameMethod g = new GameMethod();
		Player p = new Player();
		int set = g.getSet();
		boolean mode = g.getMode();
		Random rnd = new Random();
		//ArrayList <String> team1totalscore = new ArrayList <String>();
		boolean a= true;
		
	
		String [][] playername = p.Inputplayer(mode);
		p.getteam1player(playername);
		p.getteam2player(playername);
		
		while(a) {
			g.pointWinner(rnd.nextInt(2)+1, set);
		}
}
}
		