package algorithm_study;

import java.io.*;
import java.util.*;

public class distance_of_nodes_6day {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int case_t = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= case_t; t++) {
			List<List<Integer>> graph = new ArrayList<>();
			Deque<Integer> queue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// Boolean은 Wraper type이라 초기값이 없다. boolean으로 배열을 생성해야 초기값이 false
			boolean[] visited = new boolean[N + 1];
			int[] distance = new int[N + 1];
			
			// index를 Node 번호로 사용하고 있기에 graph는 1 ~ N의 Node 번호 기반 접근을
			// 지원해야 한다.
			// 그렇기에 i가 N이 될 까지 for문으로 ArrayList를 생성해야 한다 (0번은 사용 안 함)
			for (int i = 0; i <= N; i ++) {
				graph.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < E; i ++) {
				st = new StringTokenizer(br.readLine());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				
				graph.get(node1).add(node2);
				graph.get(node2).add(node1);
			}
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			queue.addLast(start);
			visited[start] = true;
			
			while(!queue.isEmpty()) {
				int cur = queue.pollFirst();
				
				if (cur == end)
					break;
				
				for (int next : graph.get(cur)) {
					
					if (!visited[next]) {
						visited[next] = true;
						distance[next] = distance[cur] + 1;
						queue.addLast(next);
					}
				}
			}
			System.out.println("#" + t + " " + distance[end]);
		}
	}
}
//이해
/*

*/

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
1) 자바에서 graph 표현
간선에 가중치가 없고 방향이 없는 경우엔
간선을 Integer 객체로 표현해도 된다.
즉, ArrayList<ArrayList<Integer>>로 graph를 표현할 수 있다.
또한 distance[], visited[] 배열을 사용해서 그래프 이동을 표현할 수 있다.

2) ArrayDequeue 사용 (stack, queue 목적)
queue: addLast, pollFirst 사용
stack: addLast, pollLast 사용
*/

//input
/*
3
6 5
1 4
1 3
2 3
2 5
4 6
1 6
7 4
1 6
2 3
2 6
3 5
1 5
9 9
2 6
4 7
5 7
1 5
2 9
3 9
4 8
5 3
7 8
1 9
(output)

*/

//다른 사람 코드
/*

*/