package JavaTenisProject;

import java.util.Arrays;
import java.util.Scanner;

public class Player {
   String team1;
   String team2;


   public static String[][] Inputplayer(boolean mode) {
      Scanner scanner = new Scanner(System.in);
      int playernum=1;               //단식 복식에 따른 플레이어 수 선언
      
      

      if(mode) {
         playernum=1;         //mode가 true면 단식
         System.out.println("단식 경기 팀당 1명 입력");
      }
      else {
         playernum=2;               //mode가 false면 복식
         System.out.println("복식 경기 팀당 2명 입력");
      }

      String [][] playername= new String [2][playernum];   //플레이어 이름을 2차원 배열로 1행에는 team1 2행에는 team2 저장

      for(int i=0;i<playernum*2;i++) {
         System.out.printf("%d팀 선수 이름을 입력해 주세요.",i<playernum?1:2);
         if(i<playernum)    playername[0][i]=scanner.next();                  //1팀 입력
         else    playername[1][i-playernum]=scanner.next();                  //2팀 입력
      }
      return playername;                                                               //선수 이름 저장한 배열을 리턴
   }

   public static String getteam1player(String[][] playername) {            //팀1의 선수를 가져오는 메서드
      String team1player = "" ;         //팀 1의 선수를 저장할 배열
      for(int i=0;i<playername[0].length;i++) {                                    //playername의 배열 수 만큼
         if(i==1) {team1player+=", ";}                                                   //복식경기에서 두번째 선수 입력 시 ,를 추가하여 이름을 구분해준다.
         team1player+=playername[0][i];                                             //team1선수는 playername0행에 있는 이름 저장
      }
      System.out.println("team1: "+team1player);
      return team1player;                                                               //team1의 선수이름이 저장되어 있는 배열 리턴
   }

   public static String getteam2player(String[][] playername) {            //팀2의 선수를 가져오는 메서드
      String team2player ="";                                                         //team2의 명단을 입력할 변수 선언
      for(int i=0;i<playername[1].length;i++) {   
         if(i==1) team2player+=", ";                                                   //복식경기에서 두번째 선수 입력 시 ,를 추가하여 이름을 구분해준다.
         team2player+=playername[1][i];
      }
      System.out.println("team2: "+team2player);
      return team2player;
   }
}

