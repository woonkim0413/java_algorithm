package algorithm_study;

import java.util.*;
import java.io.*;

public class hemburger_diet_5215 {

	static List<int[]> list = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int calLimit;
	static int maxTaste;
	static int N;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t ++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			calLimit = Integer.parseInt(st.nextToken());
			
			// list에 값 채우기
			for (int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine());
				
				list.add(new int[] {
						Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken())
						}
				);
			}	
			
			maxTaste = 0;
			bfs(1, 0, 0);
			bfs(1, list.get(0)[0], list.get(0)[1]);
			
			System.out.println("#"+t+" "+maxTaste);
		}
	}
	
	// index는 현재 deepth, calSume은 이전 deepth calorie 더한 값
	static void bfs(int index, int calTaste, int calSum) {
		if (calSum > calLimit)
			return;
		
		if (calTaste > maxTaste)
			maxTaste= calTaste;
		
		// 더이상 계산할 요소가 없으니 종료
		if (index == N)
			return;

		bfs(index + 1, calTaste, calSum);
		bfs(index + 1, calTaste + list.get(index)[0], calSum + list.get(index)[1]);
	}
}

//로직 (논리 + 코드 레벨)
/*

int[2]를 원소로 같는 ArrayList를 생성한다.
input으로 얻은 값을 ArrayList에 넣는다.
해당 list를 bfs 완탐으로 탐색한다.
bfs중 칼로리가 기준 값을 초과하면 가지치기한다.

*/

//배운 것
/*
 1) Suffix Sum(뒤쪽 누적합)
 내가 짠 코드는 calLimit 기반 가지치기 외엔 완전탐색을 전제로 한다.
 좀 더 최적화를 하자면 Suffix Sum(뒤쪽 누적합)를 사용하면 된다.
 BFS를 돌리기 전에 아래처럼 i를 포함한 뒤쪽 taste누적합을 가진 배열을 만들어서 이를 기반으로
 가지치기를 할 수도 있다.
	suffixTaste = new int[N + 1];
	for (int i = N - 1; i >= 0; i--) {
	    suffixTaste[i]
	            = suffixTaste[i + 1]
	            + list.get(i)[0];
	}
 
 예를 들어 맛이 [10, 20, 30, 40]라면 suffixTaste는 아래처럼 값을 갖게 된다.
    suffixTaste[4] = 0
	suffixTaste[3] = 40
	suffixTaste[2] = 70
	suffixTaste[1] = 90
	suffixTaste[0] = 100

 근데... 이거 실전에서 사용할 수 있을지 모르겠다.
 처음이라 그런가 복잡도가 너무 높다.
 
*/

//input
/*
1
12 1200
120 180
250 320
180 220
300 400
150 160
420 550
210 260
500 700
130 140
350 480
270 300
160 190

// 예상 output
#1 1010

*/

//다른 사람 코드
/*

*/