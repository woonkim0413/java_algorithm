package algorithm_study;

import java.util.*;
import java.io.*;

public class fine_sweeper_1868 {
	
	static int[][] map;
	static int N;
	
	// 1시부터 시계방향 탐색
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] dx = {1, 1, 1, 0, -1, -1, -1, 0};
	
	// 현재 칸 주변의 지뢰 개수를 확인
	private static int checkMineNum(int y, int x) {
		int nextX;
		int nextY;
		int curNum = 0;
		
		for (int i = 0; i < 8; i++) {
			nextX = x + dx[i];
			nextY = y + dy[i];
			
			if (nextX >= 0 && nextX < N &&
				nextY >= 0 && nextY < N) {
				
				if (map[nextY][nextX] == '*') {
					curNum++;
				}
			}
		}
		
		return curNum;
	}
	
	private static void findMineNum(int y, int x) {
		int nextX;
		int nextY;
		int curNum = 0;
		
		for (int i = 0; i < 8; i++) {
			nextX = x + dx[i];
			nextY = y + dy[i];
			
			if (nextX >= 0 && nextX < N &&
				nextY >= 0 && nextY < N) {
				
				if (map[nextY][nextX] == '*') {
					curNum++;
				}
			}
		}
		
		// 숫자를 저장하면서 방문 처리
		map[y][x] = curNum + '0';
		
		// 주변 지뢰가 0개라면 인접한 칸도 자동으로 확인
		if (curNum == 0) {
			for (int i = 0; i < 8; i++) {
				nextX = x + dx[i];
				nextY = y + dy[i];
				
				if (nextX >= 0 && nextX < N &&
					nextY >= 0 && nextY < N) {
					
					if (map[nextY][nextX] == '.') {
						findMineNum(nextY, nextX);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =
				new BufferedReader(new InputStreamReader(System.in));
		
		int CT = Integer.parseInt(br.readLine());
		
		// test case
		for (int t = 1; t <= CT; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			int click = 0;
			
			// map 그리기
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			
			// 주변 지뢰가 없는 영역부터 클릭
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.' &&
						checkMineNum(i, j) == 0) {
						
						click++;
						findMineNum(i, j);
					}
				}
			}
			
			// 자동으로 열리지 않은 숫자 칸을 각각 클릭
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						click++;
						findMineNum(i, j);
					}
				}
			}
			
			System.out.println("#" + t + " " + click);
		}
	}
}

// 이해
/*
지뢰찾기 맵이 주어졌을 때 몇 번의 클릭으로
지뢰를 제외한 모든 칸에 숫자가 표시될 수 있는지 계산한다.
*/

// 로직 (논리 + 코드 레벨)
/*
	1. 2차원 배열에 지뢰찾기 맵을 저장한다.

	2. 전체 배열을 탐색하면서 현재 칸이 '.'이고,
	   주변 8방향에 지뢰가 없는 칸부터 클릭한다.

	3. 주변 지뢰가 0개인 칸을 클릭하면 findMineNum을 호출한다.

	4. findMineNum은 현재 칸에 주변 지뢰 개수를 저장한다.
	   주변 지뢰가 0개라면 인접한 '.' 칸에도 findMineNum을 호출한다.

	5. 0 영역을 모두 처리한 후에도 남은 '.' 칸은
	   자동으로 열리지 않는 숫자 칸이므로 각각 한 번씩 클릭한다.
*/

// 배운 것
/*
	1) 문제 접근법 (최소 조건이 있으면 걍 뭔가 있다고 생각하고 들어가기)
	지뢰찾기의 최소 클릭 횟수를 구하려면 배열 순서대로 클릭하면 안 된다.

	주변 지뢰가 0개인 칸을 먼저 클릭해야 연결된 0 영역과
	경계의 숫자 칸을 한 번에 열 수 있다.

	따라서 0 영역을 먼저 처리하고,
	그 후 남은 숫자 칸을 각각 클릭해야 한다.
	
2) 내가 자주 실수하는 부분
[인덱스]
□ i는 행, j는 열로 사용했는가?
□ 문자열 접근은 charAt(j)인가?
□ 배열 접근 순서가 map[y][x]로 통일됐는가?

[범위]
□ 배열에 접근하기 전에 범위를 검사했는가?
□ 0 이상, N 미만인가?

[탐색]
□ 방문 처리를 정확한 시점에 했는가?
□ BFS는 큐에 넣을 때 방문 처리했는가?
□ 재귀 호출 전에 방문·장애물·유효 범위를 확인했는가?

[상태]
□ 변경하면 안 되는 값을 덮어쓰지 않는가?
□ 방문한 상태와 방문하지 않은 상태가 명확한가?
□ 테스트 케이스마다 static 변수를 초기화했는가?

[로직]
□ 최소·최대 결과를 만드는 처리 순서를 고려했는가?
□ 카운트를 실제 행동과 같은 시점에 증가시켰는가?
□ 시작점과 도착점에서 1 차이가 나는 오프바이원 오류는 없는가?

[Java]
□ boolean 조건과 int 비교를 섞지 않았는가?
□ 여러 조건을 && 또는 ||로 정확하게 연결했는가?
□ 메서드명, 변수명, 반환형, 선언을 확인했는가?
*/