package Jungol;

import java.util.*;
import java.io.*;


public class revolving_sushi_timeLimit_2577 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Set<Integer> set = new HashSet<>();
	static List<Integer> sushiBelt = new ArrayList<>();
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int maxKind = Integer.MIN_VALUE;
		
		// 초밥 채우기
		for (int i = 0; i < N; i ++) {
			sushiBelt.add(Integer.parseInt(br.readLine().trim()));
		}
		
		// 뒤에 순환을 위한 초밥 더 채우기
		for (int i = 0; i < k; i ++) {
			sushiBelt.add(sushiBelt.get(i));
		}
		
		// 순회하며 초밥 갯수 확인
		for (int i = 0; i < N; i ++) {
			for (int j = i; j < i + k; j++) {
				set.add(sushiBelt.get(j));
			}
			// 쿠폰 넣기
			set.add(c);
			
			// maxKind 업데이트
			maxKind = maxKind < set.size() ? set.size() : maxKind;
			
			// set 초기화
			set.clear();
			
			// set의 사이즈가 d와 같아졌으면 탐색 종료
			if (maxKind == d)
				break;
		}
		
		System.out.println(maxKind);
	}
}

//로직 (논리 + 코드 레벨)
/*
	회전초밥 레일에서 연속해서 초밥을 먹을 때 가장 많은 가짓수를 먹을 수 있는 수를 구하는 문제이다.
	N개의 초밥을 arraylist에 저장한 뒤에 순회하면서 구하면 되는거 아닐까? 1 3 6 3 8에 보너스 쿠폰 8, 연속해서
	먹어야 하는 초밥이 3개라면 1 3 6 +8, 3 6 3 + 8, 6 3 8 + 8, 3 8 1 + 8, 8 1 3 + 8 이런 식으로
	모든 경우의 수를 확인해보는것이다.
	
	숫자를 셀 땐 set을 사용하여 저장하고 마지막에 set size를 출력하는 방식이 좋을 것 같다. (중복되는 수 저장 x)
*/

//배운 것
/*

*/

//input
/*
입력
8 50 4 7 
2 
7 
9 
25 
7 
9 
7 
30
(output)

*/

//다른 사람 코드
/*

*/