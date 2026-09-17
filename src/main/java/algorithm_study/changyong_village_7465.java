package algorithm_study;

import java.util.*;
import java.io.*;

// 이거 다음에 jo religion 풀어보기
// programers network_level3도 union find 사용함
public class changyong_village_7465 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] unionSet;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); 
			int M = Integer.parseInt(st.nextToken());
			
			unionSet = new int[N+1];
			unionSet[0] = -1;
			
			// unionSet initialize
	        for (int i = 1; i <= N; i++) {
	            unionSet[i] = i;
	        }
			
	        // 집합 만들기
			for (int i = 0; i < M; i ++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			// 집합 갯수 세기
			int result = 0;
			for (int i = 1; i <= N; i ++) {
				if (unionSet[i] == i)
					result ++;
			}
			
			// 출력
			System.out.println("#"+t+" "+result);
			
			 
		}
	}
	
	static void union(int a, int b) {
		int aParent = find(a); 
		int bParent = find(b);
		
		if (aParent == bParent)
			return ;
		else if (aParent > bParent) {
			unionSet[bParent] = aParent;
		} else {
			unionSet[aParent] = bParent;
		}
	}
	
	static int find(int cur) {
		if (unionSet[cur] == cur)
			return cur;
		
		unionSet[cur] = find(unionSet[cur]); 
		return unionSet[cur];
	}
}

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
 1) union 양식 눈에 익히기
 다양한 방법이 있지만 나는 root인 경우 unionSet[i] == i이도록 유지하자
 union은 크게 unionSet 배열과 union(), find() method를 사용하여 구현하도록 하자.
*/

//input
/*
2
6 5
1 2
2 5
5 1
3 4
4 6
6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3
(output)

*/

//다른 사람 코드
/*

*/
