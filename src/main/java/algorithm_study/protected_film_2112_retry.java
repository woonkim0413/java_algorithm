package algorithm_study;

import java.util.*;
import java.io.*;

public class protected_film_2112_retry {
	static int min;
	static char[][] map;
	static int H;
	static int W;
	static int K;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t ++) {
			H = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			
			map = new char[H][W];
			
			for (int i = 0; i < H; i ++) {
				for (int j = 0; j < W; j ++) {
					map[i][j] = (char) (sc.nextInt() + '0'); // 숫자 문자열을 받아야 하는 경우에 '0'를 더해야 함
				}
			}
			
			min = Integer.MAX_VALUE;
			bfs(0, '1', 0);
			bfs(0, '0', 0);
			bfs(0, 'X', 0);
			System.out.println("#"+t+" "+min);
		}
	}
	
	// count는 이전까지의 약품 처리 숫자; 현재 bfs에서 cur에 따라 증가시켜서 재귀 호출 시점에 전달
	static void bfs(int index, char cur, int count) {
		if (count >= min) { // 원래 if (index == H)도 ||로 검사함 근데 여기서 검사하면 H-1 행에 대해 check 검사를 안 하게 됨 (gpt)
			return ;
		}
		
		if (index == H) { // index가 H보다 작은 경우에도 check를 호출하면 호출 횟수가 너무 많아진다.
		    if (check())
		        min = Math.min(count, min);
		    return;
		}
		
		char[] temp = map[index].clone();
		
		count = treatment(index, cur, count);
		
		bfs(index + 1, '1', count);
		bfs(index + 1, '0', count);
		bfs(index + 1, 'X', count);
		
		backup(index, temp); // 이전 작업 복구
	}
	
	static boolean check() {
		out:for (int w = 0; w < W; w++) {
			char cur_char = map[0][w];
			int sameCount = 0;
			
			for (int c = 0; c < H; c++) {
				if (map[c][w] == cur_char)
					sameCount ++;
				else {
					cur_char = map[c][w];
					sameCount = 1;
				}
				
				if (sameCount == K) {
					continue out;
				}
			}
			
			// for문 안의 break를 타지 못했다는 뜻은 해당 열은 조건을 충족하지 못했다는 의미임
			return false;
		}
		
		// 모든 for문을 break로 통과했기에 모든 열이 조건을 만족함
		return true;
	}
	
	static int treatment(int index, char cur, int count) {
		if (cur == 'X')
			return count;
		
		for (int i = 0; i < W; i ++) {
			map[index][i] = cur;
		}
		return count + 1;
	}
	
	static void backup(int index, char[] arr) {
		map[index] = arr;
	}
}

//로직 (논리 + 코드 레벨)
/*
두께가 1~13임 그렇기에 3^13의 완탐을 하면 모든 경우의 수를 확인할 수 있다 (150만 경우의 수)
제한 시간이 5초니 쌉가능임
- 각 층마다 유지, A처리, B처리로 분기함
- A,B 재귀 호출 전에 count를 늘리고 호출이 끝다면 다시 상태를 복원하는 백트레킹 사용
- A,B로 한 줄을 변경하는 method 필요
- 약품 처리마다 완료 조건 검사
- 최소 약품 처리 숫자 이상으로 count가 증가하면 가지치기 사용

*/

//배운 것
/*

*/

//input
/*
2
6 8 3         
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
6 8 3         
1 1 1 1 0 0 1 0
0 0 1 1 0 1 0 1
1 1 1 1 0 0 1 0
1 1 1 0 0 1 1 0
1 1 0 1 1 1 1 0
1 1 1 0 0 1 1 0
(output)

*/

//다른 사람 코드
/*

<<< 창선이 코드 >>>
약품 처리를 level로 다루어 bfs로 문제를 풀었다.
int a, b 변수의 각 bit를 사용하여 row에 대한 약품 처리 유무를 표현했다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int D, W, K;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.printf("#%d %d%n", tc, search());
        }
    }

    static int search() {
        ArrayDeque<State> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[1 << D][1 << D];
        q.offer(new State(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (check(cur.a, cur.b)) {
                return cur.count;
            }

            for (int i = 0; i < D; i++) {
                if ((cur.a & (1 << i)) > 0 || (cur.b & (1 << i)) > 0) {
                    continue;
                }
                
                int na = cur.a | (1 << i);
                if (!visited[na][cur.b]) {
                    q.offer(new State(na, cur.b, cur.count + 1));
                    visited[na][cur.b] = true;
                }
                int nb = cur.b | (1 << i);
                if (!visited[cur.a][nb]) {
                    q.offer(new State(cur.a, nb, cur.count + 1));
                    visited[cur.a][nb] = true;
                }
            }
        }

        return -1;
    }

    static class State {
        int a, b, count;

        State(int a, int b, int count) {
            this.a = a;
            this.b = b;
            this.count = count;
        }
    }

    static boolean check(int a, int b) {
        for (int c = 0; c < W; c++) {
            int count = 0;
            int mode = -1;
            for (int r = 0; r < D; r++) {
                int curcell = arr[r][c];
                if ((a & (1 << r)) > 0) {
                    curcell = 0;
                }
                if ((b & (1 << r)) > 0) {
                    curcell = 1;
                }

                if (mode == curcell) {
                    count++;
                    if (count >= K) {
                        break;
                    }
                } else {
                    count = 1;
                    mode = curcell;
                }
            }

            if (count < K) {
                return false;
            }
        }
        return true;
    }
}

<<< 재우 코드 >>>
나는 각 행마다 A, B처리 및 미처리를 각각 분기시킨다.
반대로 재우는 약품 처리 횟수는 0 ~ H(액정 높이) 범위를 갖으니
hCr을 사용하여 0부터 r까지의 약품 처리의 횟수에 따른 조합 갯수를 각각 구하여 검사하는 식으로 풀었다.
-> 시간 복잡도가 나보다 더 클 것 같은데 오히려 작았다 이유가 뭐지?
-> 약품 처리가 적은 조합부터 for을 돌리기에 초반에 결과가 구해지면 모든 경우를 보지 않고 바로 리턴할 수 있다.
   그렇기에 성능적인 이점이 발생할 수 있다.

class Solution
{
    static int d, w, k, totalCnt; // 보호 필름의 두께, 가로 크기, 합격 기준
    static int[][] film;
    static boolean chk;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            film = new int[d][w];
            totalCnt = 0;
 
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int i = 0; i < d; i++) { // d보다 더 적어도 됨
                chk = false;
 
                dosing(0, 0, i);
 
                if (chk) {
                    totalCnt = i;
                    break;
                }
            }
            System.out.printf("#%d %d%n", tc, totalCnt);
        }
    }
 
    static boolean check() { // 보호 필름이 테스트를 통과 하였는지 여부 확인
        if (k == 1) // k가 1이면 항상 통과
            return true;
        for (int c = 0; c < w; c++) { // 검사할 열 선택
            int cnt = 1;
 
            for (int r = 1; r < d; r++) { // 선택한 열을 기준으로 행을 순차적으로 감사하여 1이 연속되어 나오면 cnt++
                if (film[r][c] == film[r - 1][c])
                    cnt++;
                else
                    cnt = 1;
 
                if (cnt >= k) // cnt가 k 이상이면 그 열은 통과
                    break;
            }
            if (cnt < k) // 테스트에 실패하면 즉시 false 반환
                return false;
        }
        return true; // 전부 통과하면 true 반환
    }
 
    static void dosing(int cnt, int start, int r) { // 약품 투입 함수 dfs로 조합 구현
        if (chk) return; // 이미 정답을 찾은 경우 새로 호출된 dfs를 return
 
        if (cnt == r) {
            if (check())
                chk = true;
            return;
        }
 
        for (int i = start; i < d; i++) {
            int[] temp = film[i].clone(); // 행만 복사한 임시 배열
 
            for (int j = 0; j < w; j++) { // 행을 0으로 만드는 약품
                film[i][j] = 0;
            }
            dosing(cnt + 1, i + 1, r);
 
            for (int j = 0; j < w; j++) { // 행을 1로 만드는 약품
                film[i][j] = 1;
            }
            dosing(cnt + 1, i + 1, r);
             
            film[i] = temp; // 원복
             
            if (chk) break; // 이미 정답을 찾은 경우 다음 행으로 넘어가지 않음
 
        }
    }
}

*/