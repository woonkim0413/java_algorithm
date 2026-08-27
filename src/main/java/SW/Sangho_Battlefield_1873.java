package algorithm_study;

import java.util.*;
import java.io.*;

public class Sangho_Battlefield_1873 {
	private static char[][] map;
	private static int[] map_size = new int[2]; // 0 = h, 1 = w;
	private static int[] tank_cur = new int[2]; // 0 = x, 1 = y;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		int case_t;
		
		case_t = Integer.parseInt(br.readLine());
		for (int t = 0; t < case_t; t ++) {
			st = new StringTokenizer(br.readLine());
			int act_num;
			map_size[0] = Integer.parseInt(st.nextToken());
			map_size[1] = Integer.parseInt(st.nextToken());
			map = new char[map_size[0]][map_size[1]];
			
			// map 그리기
			for (int i = 0; i < map_size[0]; i ++) {
				str = br.readLine();
				// System.out.println("테스트: " + str);
				for (int j = 0; j < map_size[1]; j ++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
						tank_cur[0] = i;
						tank_cur[1] = j;
					}
				}
			}
			
			// 동작 횟수 및 동작 받기
			act_num = Integer.parseInt(br.readLine());
			str = br.readLine();
			
			// 동작 수행
			char cur_act;
			int temp_x = 0;
			int temp_y = 0;
			for (int i = 0; i < act_num; i ++) {
				cur_act = str.charAt(i);
				// System.out.println((i + 1) + "번째 동작 실행 " + cur_act);
				if (cur_act == 'U') {
					map[tank_cur[0]][tank_cur[1]] = '^';
					
					if (tank_cur[0] == 0)
						continue;
					
					// System.out.println("pass");
					temp_y = tank_cur[0] - 1;
					temp_x = tank_cur[1];
					
					if (map[temp_y][temp_x] == '*' || map[temp_y][temp_x] == '#' 
							|| map[temp_y][temp_x] == '-')
						continue;
					map[temp_y][temp_x] = '^';
					map[tank_cur[0]][tank_cur[1]] = '.';
					tank_cur[0] = temp_y;
					tank_cur[1] = temp_x;
				}
				else if (cur_act == 'S') {
					if (map[tank_cur[0]][tank_cur[1]] == '^') {
						for (int k = tank_cur[0]; k >= 0; k --) {
							if (map[k][tank_cur[1]] == '#')
								break;
							if (map[k][tank_cur[1]] == '*') {
								map[k][tank_cur[1]] = '.';
								break;
							}
						}
					}
					if (map[tank_cur[0]][tank_cur[1]] == 'v') {
						for (int k = tank_cur[0]; k < map_size[0]; k ++) {
							if (map[k][tank_cur[1]] == '#')
								break;
							if (map[k][tank_cur[1]] == '*') {
								map[k][tank_cur[1]] = '.';
								break;
							}
						}
					}
					if (map[tank_cur[0]][tank_cur[1]] == '<') {
						for (int k = tank_cur[1]; k >= 0; k--) {
							if (map[tank_cur[0]][k] == '#')
								break;
							if (map[tank_cur[0]][k] == '*') {
								map[tank_cur[0]][k] = '.';
								break;
							}
						}
					}
					if (map[tank_cur[0]][tank_cur[1]] == '>') {
						for (int k = tank_cur[1]; k < map_size[1]; k++) {
							if (map[tank_cur[0]][k] == '#')
								break;
							if (map[tank_cur[0]][k] == '*') {
								map[tank_cur[0]][k] = '.';
								break;
							}
						}
					}
				}
				else if (cur_act == 'D') {
					map[tank_cur[0]][tank_cur[1]] = 'v';
					
					if (tank_cur[0] == map_size[0] - 1)
						continue;
					
					temp_y = tank_cur[0] + 1;
					temp_x = tank_cur[1];
					
					if (map[temp_y][temp_x] == '*' || map[temp_y][temp_x] == '#' 
							|| map[temp_y][temp_x] == '-')
						continue;
					map[temp_y][temp_x] = 'v';
					map[tank_cur[0]][tank_cur[1]] = '.';
					tank_cur[0] = temp_y;
					tank_cur[1] = temp_x;	
				}
				else if (cur_act == 'L') {
					map[tank_cur[0]][tank_cur[1]] = '<';
					
					if (tank_cur[1] == 0)
						continue;
					
					temp_y = tank_cur[0];
					temp_x = tank_cur[1] - 1;
					
					if (map[temp_y][temp_x] == '*' || map[temp_y][temp_x] == '#' 
							|| map[temp_y][temp_x] == '-')
						continue;
					map[temp_y][temp_x] = '<';
					map[tank_cur[0]][tank_cur[1]] = '.';
					tank_cur[0] = temp_y;
					tank_cur[1] = temp_x;
				}
				else if (cur_act == 'R') {
					map[tank_cur[0]][tank_cur[1]] = '>';
					
					if (tank_cur[1] == map_size[1] - 1)
						continue;
					
					temp_y = tank_cur[0];
					temp_x = tank_cur[1] + 1;
					
					if (map[temp_y][temp_x] == '*' || map[temp_y][temp_x] == '#' 
							|| map[temp_y][temp_x] == '-')
						continue;
					map[temp_y][temp_x] = '>';
					map[tank_cur[0]][tank_cur[1]] = '.';
					tank_cur[0] = temp_y;
					tank_cur[1] = temp_x;
				}
			}
			System.out.print("#" + (t + 1) + " ");
			for (int i = 0; i < map_size[0]; i ++) {	
				System.out.println(new String(map[i]));
			}
			System.out.println();
		}
	}
}

//이해
/*
	전차 시뮬레이션이다.
	맵이 있고 입력에 따라 탱크가 맵과 상호작용한다.
	동작 갯수를 사전에 제공하며 모든 동작을 마친 뒤의 맵을 출력한다
*/

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
  1) 일반화 하는 법 (나중에 해당 문제 다시 풀 때 꼭 신경쓰기)
  문제 다 풀고 SG2라는 분의 코드를 보았다.
  SG2라는 분은 탱크 방향 배열, 방향 벡터 배열, 입력 배열를
  index를 기준으로 서로 연관성 있게 묶었다.
  예를 들면 탱크 방향 배열의 0번째 index가 '<'이면 백터 배열의
  dx, dy index 0은 (-1, 0)이며 입력 배열 index 0은 'L'로 묶었다.
  이렇게 설정하면 특정 index 하나를 기준으로 여러 정보들을 가져올 수 있게 된다.
  ex)
      public static void move(char ch, int x, int y, char cur_d) {
        for (int i=0; i<4; i++) {
            if (ch == direc[i]) {
                int next_x = x+dx[i];
                int next_y = y+dy[i];
                 
                if (isOutMap(next_x, next_y)) { // 맵 바깥인 경우 => 움직일 수 없음, 현재 위치에서 방향 전환
                    map[x][y] = tank[i];
                    Solution.cur_d = tank[i];
                    return;
                }
*/

//input
/*
4
6 8
........
..*#....
..-.....
...^.*..
....#...
........
16
SSRRUUSLLDDRRSSU
7 7
.......
.***...
.*#*...
.*>....
...-...
..#....
.......
18
SSUULLSSRRDDSSLLUUR
6 9
.........
..#.*....
...-.....
.*..v....
.....*...
.........
20
UUSRRSSLLDDSSRRUULLD
8 8
........
..*..#..
.-......
...*....
....<...
..#..*..
....-...
........
24
RRSSUULLSSDDRRUUSSLLDDSS

(출력)
#1 ........
..*#....
..-.^...
........
....#...
........

#2 .......
.***...
.*#*...
.......
...-...
..#.>..
.......

#3 .........
..#......
...-.....
.*..v....
.....*...
.........

#4 ........
..*..#..
.-......
...*....
....v...
..#..*..
....-...
........
*/

//다른 사람 코드
/*
import java.io.*;
import java.util.*;
public class Solution {
    static int H, W;
    static char[][] map;
    static int N;
    static char[] user_move;
     
    // UDLR (상하좌우) 단위 벡터
    static int[] dx = {-1, 1, 0, 0};    
    static int[] dy = {0, 0, -1, 1};
    static char[] tank = {'^', 'v', '<', '>'};        // 탱크가 바라보는 방향
    static char[] direc = {'U', 'D', 'L', 'R'};     
    static int x, y;    // 현재 위치
    static char cur_d;  // 현재 방향
             
    static StringBuilder sb = new StringBuilder();
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
             
            map = new char[H][W];
             
             
            for (int i=0; i<H; i++) {
                String row = br.readLine();
                for (int j=0; j<W; j++) {
                    map[i][j] = row.charAt(j);
                    for (int k=0; k<4; k++) {                        
                        if (map[i][j] == tank[k]) { // 현재 탱크의 위치와 방향 기록
                            x = i;
                            y = j;
                            cur_d = map[i][j];
                        }
                    }
                }
            }
             
            N = Integer.parseInt(br.readLine());
     
            String input = br.readLine();
            user_move = new char[N];
            for (int i=0; i<N; i++) {
                user_move[i] = input.charAt(i);
            }
             
            // 입력 처리 끝, 로직 시작
            for (int i=0; i<N; i++) {
                char ch = user_move[i];
                if (ch == 'S') {    // 포탄 발사 
                    shoot(x, y, cur_d);
                } else {                    
                    move(ch, x, y, cur_d);
                }
            }
             
            // 결과 입력
            sb.append("#").append(tc).append(" ");
            for (int i=0; i<H; i++) {
                for (int j=0; j<W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
         
    }
     
     
    // 좌표가 맵 바깥인지 판단하는 함수
    public static boolean isOutMap(int r, int c) {
        return (r < 0 || c < 0 || r >= H || c >= W);
    }
     
     
    // 현재 위치 x, y 와 방향 cur_d에서 움직임 ch 가 들어왔을 때의 모든 처리와 맵 변경
    public static void move(char ch, int x, int y, char cur_d) {
        for (int i=0; i<4; i++) {
            if (ch == direc[i]) {
                int next_x = x+dx[i];
                int next_y = y+dy[i];
                 
                if (isOutMap(next_x, next_y)) { // 맵 바깥인 경우 => 움직일 수 없음, 현재 위치에서 방향 전환
                    map[x][y] = tank[i];
                    Solution.cur_d = tank[i];
                    return;
                }
 
                // (맵 안쪽이지만) 물 or 벽(강철, 벽돌) 이면 => 위와 마찬가지
                char next_ch = map[next_x][next_y];
                if (next_ch == '#' || next_ch == '*' || next_ch == '-' ) {
                    map[x][y] = tank[i];
                    Solution.cur_d = tank[i];
                    return; 
                }   
                 
                // (else) 평지 => 한 칸 이동
                map[x][y] = '.';    // 원래 자리에 평지를 넣고
                map[next_x][next_y] = tank[i];  // 이동한 곳에 방향전환한 탱크를 넣기
                 
                // 실제 좌표, 방향 변경
                Solution.x = next_x;
                Solution.y = next_y;
                Solution.cur_d = tank[i];   
                return;
            }
        }   
    }
     
    // 포탄 발사
    public static void shoot(int x, int y, char cur_d) {
        for (int i=0; i<4; i++) {
            if (tank[i] == cur_d) {     // 현재 방향으로 포탄 발사 시도
                int next_x = x+dx[i];
                int next_y = y+dy[i];
                 
                while (!isOutMap(next_x, next_y) ) {    // 맵 내부 인 동안 반복 
                    char next_ch = map[next_x][next_y];
 
                    if (next_ch == '#') return; // 강철 벽 => 아무 일 X
                     
                    if (next_ch == '*') {   // 벽돌 벽 => 평지로 바꿈
                        map[next_x][next_y] = '.';
                        return;
                    }
                     
                    // else) 맵 안쪽 인데 벽을 안 만난경우 (평지 or 물) => 다음 칸으로 이동
                    next_x += dx[i];
                    next_y += dy[i];    
                }
                return;
            }
        }
    }
}
*/
