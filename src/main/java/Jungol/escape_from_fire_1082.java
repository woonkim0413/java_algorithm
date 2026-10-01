package Jungol;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class escape_from_fire_1082 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Deque<int[]> queue;
	static int[][] visited;
	static StringTokenizer st;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		queue = new ArrayDeque<>();
		visited = new int[R][C];
		
		// map 그리기
		int[] start = new int[2];
		map = new char[R][C]; // 0: row, 1: col
		for (int i = 0; i < R; i ++) {
			String row = br.readLine();
			for (int j = 0; j < C; j ++) {
				
				map[i][j] = row.charAt(j);
				
				if (map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
					map[i][j] = '.'; // 밑에 불지르기 코드 용이성을 위해
					// System.out.printf("start r:%d / c:%d\n", start[0], start[1]);
				}
				
				
			}
		}
		
		// 시뮬
		queue.offer(start);
		visited[start[0]][start[1]] = 1;
		int time = 0;
		end : while (!queue.isEmpty()) {
			time++;
		
			int size = queue.size();
			
			// 불지르기
			fireing(R, C);
			
			// bfs 한 level
			for (int i = 0; i < size; i ++) {
				int[] cur = queue.poll();
				// System.out.printf("r:%d / c:%d (time: %d)\n", cur[0], cur[1], time);
				
				// 불 번지기 전에 위치했으니 불이 해당 위치로 번지기 전에 이동하면 정상 동작
				// if (map[cur[0]][cur[1]] == '*') 
				//	 continue;
				
				for (int j = 0; j < 4; j ++) {
					int nextR = cur[0] + dr[j];
					int nextC = cur[1] + dc[j];
						
					// 유효성 검사; 범위 + 바위 체크 + 이미 방문한 곳
					if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || map[nextR][nextC] == 'X' 
							|| map[nextR][nextC] == '*' || visited[nextR][nextC] == 1) 
						continue ;
					
					
					if (map[nextR][nextC] == 'D') {
						System.out.println(time);
						queue.add(new int[] {1, 1}); // 마지막에 찾게되는 경우 impossible 출력 방지
						break end;
					}
					
					queue.offer(new int[] {nextR, nextC});
					
					visited[nextR][nextC] = 1;
				}
			}
		}
		if (queue.size() == 0) {
			System.out.println("impossible");
		}
	}
	
	static void fireing(int R, int C) {
		// map 복사
		char[][] temp = new char[R][C];
		for (int i = 0; i < R; i ++) {
			temp[i] = map[i].clone();
		}
		
		for (int i = 0; i < R; i ++) {
			for (int j = 0; j < C; j ++) {
				
				if (map[i][j] == '*') {
					
					for (int dir = 0; dir < 4; dir ++) {
						int nextR = i + dr[dir];
						int nextC = j + dc[dir];
						
						if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || map[nextR][nextC] != '.') {
							continue;
						}
						
						temp[nextR][nextC] = '*';
					}
				}
			}
		}
		map = temp;
	}
}
//로직 (논리 + 코드 레벨)
/*
	최단거리: bfs
	불은 1분마다 인접한 4개의 칸으로 이동, 바위와 도착지를 태우지 못 함
	S -> D의 최소 이동 횟수 출력 (불가능하면 impossible)
	
	1) bfs 한 cycle마다 map의 불을 업데이트
	2) 큐에서 원소를 꺼낸 뒤 해당 좌표가 현재 불에 휩싸였는지 등을 체크
	3) 이동 안 했다면 visited 체크 후 이동하기
	4) map을 다시 그리는 작업에서 그리는 행위가 다음으로 그리는 행위에 영향을 주는 경우엔 복사본에 그린 뒤 원본으로 옮기기
*/

//배운 것
/*

*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/