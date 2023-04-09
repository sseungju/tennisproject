package JavaTenisProject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameMethod extends FileManager{

	// 2팀과 3개의 점수 체계(포인트, 게임, 세트)
	int [][] scores = new int[2][3];
	DispScoreBoard dp = new DispScoreBoard();
	public static ArrayList <String> team1totalscore= new ArrayList<String>();
	public static ArrayList <String> team2totalscore= new ArrayList<String>();

	public int getSet() {
		//세트
		Scanner scanner = new Scanner(System.in);

		int selectSet = 0;

		String nSet = String.format("%d세트", selectSet);

		do {

			System.out.print("몇 세트(3, 5) 경기 선택하세요 : ");
			selectSet = scanner.nextInt();

			//3세트
			if(selectSet == 3) {
				nSet = String.format("%d세트", selectSet);
				System.out.println("> " + nSet + "를 선택하셨습니다.");
			} // if

			//5세트
			else if(selectSet == 5) {
				nSet = String.format("%d세트", selectSet);
				System.out.println("> " + nSet + "를 선택하셨습니다.");
			} // else if

			else System.out.println("3 혹은 5를 선택해주세요.");

		} while( selectSet != 3 && selectSet != 5 );

		return selectSet;
	}

	public boolean getMode() {
		// 단식이면 true, 복식이면 false
		boolean mode = true;  
		String modeStr = "";

		do {

			Scanner scanner = new Scanner(System.in);
			System.out.print("단식, 복식 경기 선택하세요 : ");
			modeStr = scanner.next();

			//단식
			if( modeStr.equals("단식") ) {
				System.out.println("> 단식을 선택하셨습니다.");
				mode = true;
			} // if

			// 복식
			else if( modeStr.equals("복식")) {
				System.out.println("> 복식을 선택하셨습니다.");
				mode = false;
			} // else if

			else System.out.println("단식 혹은 복식을 선택해주세요.");

		} while( !modeStr.equals("단식") && !modeStr.equals("복식") );

		return mode;
	}

	public void pointWinner(int p, int set) {

		String [] points = {"0", "15", "30", "40", "AD", "WIN"};

		scores[p-1][0]++;
		dp.dispPointScore(scores[0][0], scores[1][0]);

		// 포인트가 3-3(40-40)인 경우 듀스 룰 적용
		if (scores[0][0] == 3 && scores[1][0] == 3) {
			playDeuce(set);
		}

		// 한 팀의 포인트가 3(40) 이상이 되면 게임 점수 획득
		if (scores[p-1][0] > 3) {
			team1totalscore.add(points[scores[0][0]]);	//팀 1 점수 저장
			team2totalscore.add(points[scores[1][0]]);	//팀 2 점수 저장
			//			System.out.print(team1totalscore.toString());
			plusGame(p, set);
			scores[0][0] = scores[1][0] = 0;
		}

	}

	public void plusGame(int p, int set) {

		scores[p-1][1]++;
		dp.dispGameScore(scores[0][1], scores[1][1]);

		// 한 팀이 다른 팀보다 2게임 이상 앞서고 6게임 이상 먼저 따면 세트 점수 획득
		if (scores[p-1][1] - scores[2-p][1] >= 2 && scores[p-1][1] >= 6) {
			plusSet(p, set);
			team1totalscore.add("setchange");		//세트별 구분자 :
			team2totalscore.add("setchange");		//세트별 구분자 :
			scores[0][1] = scores[1][1] = 0;
		}

	}

	public void plusSet(int p, int set) {

		scores[p-1][2]++;
		dp.dispSetScore(scores[0][2], scores[1][2]);

		// 한 팀의 세트 점수가 총 세트 수의 절반 이상이면 승리
		if (scores[p-1][2] >= (set/2+1)) {
			System.out.println("> Team" + p + " 승리");
			totalscoreprint(p);

			System.exit(-1);		//시스템 종료

		} 

	}

	public void playDeuce(int set) {
		System.out.println("=== Deuce ===");
		Random rnd = new Random();

		// 한 팀이 다른 팀보다 2점 선취할 때까지 반복
		while (true) {
			int p = rnd.nextInt(2)+1;
			scores[p-1][0]++;  // Advantage In
			scores[2-p][0]--;    // Advantage Out
			if (scores[0][0] - scores[1][0] == 2 || scores[1][0] - scores[0][0] == 2) {
				if(scores[0][0]-scores[1][0]==2) {                      //듀스 상황일때 점수 입력
					team1totalscore.add("AD");
					team2totalscore.add("40");
				}
				else {
					team1totalscore.add("40");
					team2totalscore.add("AD");
				}
				plusGame(p, set);
				scores[0][0] = scores[1][0] = 0;
				break;
			}
		}

	}
	
	public static void totalscoreprint(int p) {
		int set=1;

		int team1gamewin=0;                     //1세트에서 획득한 team1의 점수
		int team2gamewin=0;
		String setreport="";                        //세트별 점수 문자열

		String team1line="team1: (1세트) ";   //team1의 score 정리할 문자열
		String team2line="team2: (1세트) ";   //team2의 score를 정리할 문자열

		for(int i =0; i <team1totalscore.size(); i ++) {
			if(team1totalscore.get(i).equals("setchange")) {   //배열안에 포함된 setchange를 구분자로 세트를 나눈다.
				if(team1gamewin>team2gamewin) 
					setreport += set+"세트 "+team1gamewin +":"+team2gamewin+ " team1 승리\n";
				else setreport += set+"세트 "+team1gamewin +":"+team2gamewin+ " team2 승리\n";
				set++;                                                         //세트 +1
				team1line += "\t("+set+"세트)";
				team2line += "\t("+set+"세트) ";                     
				team1gamewin=team2gamewin=0;               //세트별 점수 초기화
			}//if

			else{
				team1line += team1totalscore.get(i)+ "\t";         //team1line에 team1totalscore 더하기
				team2line += team2totalscore.get(i)+ "\t";         //team2line에 team2totalscore 더하기
				if(team1totalscore.get(i).equals("AD")) team1gamewin++;   //한 세트 안에서 획득한 게임++
				else team2gamewin++;
			}//else
		}//for
		if(team1gamewin>team2gamewin)
			setreport += set+"세트 "+team1gamewin +":"+team2gamewin+ " team1 승리\n";
		else setreport += set+"세트 "+team1gamewin +":"+team2gamewin+ " team2 승리\n";

		String totalscore = "<총 경기 결과>\n"+p+"팀 승리\n";            //전체 score 정리할 문자열
		totalscore += setreport+"\n"+team1line+"\n"+team2line;
		txtout(totalscore);
	}


}

	