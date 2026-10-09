package Jungol;

import java.util.*;
import java.io.*;

public class music_program_1946 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] indegree;
	static List<Integer> list;
	static List<Integer> result = new ArrayList<>();
	static Deque<Integer> queue = new ArrayDeque<>();
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int order = Integer.parseInt(st.nextToken());
		// System.out.printf("N: %d / node: %d%n", N, node);
		
		indegree = new int[N + 1];
		Arrays.fill(indegree, 0); // 연습겸 사용
		
		// graph 생성 (index 0을 쓰지 않기 위해 for문 전에 하나 추가)
		graph.add(new ArrayList<>());
		for (int i = 0; i < N; i ++) {
			list = new ArrayList<>();
			graph.add(list);
		}
		
		// graph 채우기 + indegree 채우기
		for (int i = 0; i < order; i ++) {			
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken()); 
			int to; 
			
			for (int j = 1; j < num; j ++) {
				to = Integer.parseInt(st.nextToken());
				
				// System.out.println(from);
				graph.get(from).add(to);
				
				indegree[to]++;
				
				from = to;
			}
		}
		
		// 위상정렬 탐색
		for (int i = 1; i <= N; i ++) {
			if (indegree[i] == 0) {
				queue.addLast(i);
			}
		}
		
		int cur;
		while(!queue.isEmpty()) {
			// System.out.println(1);
			cur = queue.pollFirst();
			result.add(cur);
			list = graph.get(cur);
			
			for (int temp : list) {
				indegree[temp]--;
				if (indegree[temp] == 0) {
					queue.addLast(temp);
				}
			}
		}
		
		// output
		if (result.size() == N) {
			for (int i = 0; i < N; i ++) {
				System.out.println(result.get(i));
			}
		} else {
			System.out.println(0);
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
  1) Index 4 out of bounds for length 3 
  <- indext 4는 벗어났다 길이 3인 경우에 (for은 ~인 경우에, 라는 뜻으로도 쓰인다)
  
  2) queue.pollFirst() vs queue.getFirst()
  getFirst를 사용하면 queue에서 제거되지 않는다.
  pollFirst를 사용해야만 queue에서 제거된다.
  
*/

//input
/*
6 3
3 1 4 3
4 6 2 5 4
2 2 3
(output)

*/

//다른 사람 코드
/*

*/

