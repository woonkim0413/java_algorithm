package Jungol;

import java.util.*;
import java.io.*;

public class music_program_1946 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<List<Integer>> map = new ArrayList<>();
	static int[] indegree;
	static List<Integer> result = new ArrayList<>();
	static Deque<Integer> queue = new ArrayDeque<>();
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int node = Integer.parseInt(st.nextToken());
		
		indegree = new int[N + 1];
		Arrays.fill(indegree, 0); // 연습겸 사용
		
		// map 채우기 + indegree 채우기
		for (int i = 0; i < node; i ++) {
			List<Integer> list = new ArrayList<>();
			
			boolean check = false;
			
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			// 그래프 간선 채우기
			for (int j = 0; j < num; j ++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if (j > 0) { // by 채우기
					indegree[temp] ++;
				}
				list.add(temp);
			}
			map.add(list);
		}
		
		
		
	}
}


//로직 (논리 + 코드 레벨)
/*
 순서가 있는 배열이 여러개 존재할 때
 이를 판단하여 하나의 배열 순서로 만드는 알고리즘을 위상정렬이라고 한다 (정의 좀 이상함)
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

