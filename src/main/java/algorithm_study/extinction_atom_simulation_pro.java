package algorithm_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// wireless charging 조합 주석 읽어보기
public class extinction_atom_simulation_pro {
	
	  static int N = 4001;
	  static int[][] map = new int[N][N];
	  static int[] dy = {1, -1, 0, 0};
	  static int[] dx = {0, 0, -1, 1};
	  
	  public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	 
	    for (int tc = 1; tc <= T; tc++) {
	 
	      int totalEnergy = 0;
	      int atoms = Integer.parseInt(br.readLine());
	      ArrayDeque<Unit> dq = new ArrayDeque<>();
	 
	      for (int i = 0; i < atoms; i++) {
	        StringTokenizer st = new StringTokenizer(br.readLine());
	 
	        int x = (Integer.parseInt(st.nextToken()) + 1000) << 1;
	        int y = (Integer.parseInt(st.nextToken()) + 1000) << 1;
	        int dir = Integer.parseInt(st.nextToken());
	        int e = Integer.parseInt(st.nextToken());
	 
	        map[y][x] = e; // 그 위치에 있는 원자들이 가진 에너지를 누적
	        dq.addLast(new Unit(x, y, dir, e));
	      }
	 
	      // 살아있는 원자들을 이동처리
	      // 아래 while문은 먼저 모든 원자를 이동시킨 뒤 충돌을 확인하는게 아니라 원자를 이동하면서 동시에 충돌을 확인한다
	      // 그렇기에 A는 t1 위치, B는 t0위치인 경우가 중첩될 수 있으므로 일반적으론 오류가 발생할 수 있다.
	      // 그러나 해당 코드는 시간을 두 배로 늘렸기에 원자의 이동 단위가 1초가 아니라 0.5초이다.
	      // ex) 1,3 2,2의 두 원자가 있었다면 조정 후엔 2002,2006 2004,2004이 된다.
	      // 즉, 시간 조정 후엔 모든 x+y가 짝수가 되고 한 칸 이동하면 x+y가 홀수가 된다.
	      // 즉, 서로 다른 시간대의 원자는 만날 수 없으므로 해당 식은 문제가 발생하지 않는다.
	      while (!dq.isEmpty()) {
	        Unit cur = dq.pollFirst();
	 
	        if (map[cur.y][cur.x] != cur.e) { // 이 위치에서 충돌
	          totalEnergy += map[cur.y][cur.x];
	          map[cur.y][cur.x] = 0;
	          continue;
	        }
	 
	        // 이동, 현 위치에서 원자의 방향으로 다음 위치 계산
	        map[cur.y][cur.x] = 0;
	        int nx = cur.x + dx[cur.dir];
	        int ny = cur.y + dy[cur.dir];
	 
	        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
	 
	        cur.x = nx;
	        cur.y = ny;
	 
	        map[cur.y][cur.x] += cur.e;
	 
	        dq.addLast(cur);
	      }
	 
	      System.out.println("#" + tc + " " + totalEnergy);
	 
	    }
	     
	     
	  }
	 
	  static class Unit {
	    int x, y, dir, e;
	 
	    public Unit(int x, int y, int dir, int e) {
	      this.x = x;
	      this.y = y;
	      this.dir = dir;
	      this.e = e;
	    }
	 
	     
	  }
}
