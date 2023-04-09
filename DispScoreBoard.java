package JavaTenisProject;

public class DispScoreBoard {
	
	public void dispPointScore (int t1_score, int t2_score){
		String [] points = {"0", "15", "30", "40", "AD", "WIN"};
		System.out.printf("Team1 : %s \t Team2 : %s\n", points[t1_score], points[t2_score]);
	}
	
	public void dispGameScore (int t1_game, int t2_game) {
		System.out.printf("[ Team1 게임 점수 : %d \t Team2 게임 점수 : %d ]\n", t1_game, t2_game);
	}
	
	public void dispSetScore (int t1_set, int t2_set){
		System.out.printf("[ Team1 세트 점수 : %d \t Team2 세트 점수 : %d ]\n", t1_set, t2_set);
	}

}
