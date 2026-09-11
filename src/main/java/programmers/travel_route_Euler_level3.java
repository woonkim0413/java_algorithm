package programmers;

import java.util.*;

public class travel_route_Euler_level3 {

	Map<String, PriorityQueue<String>> graph = new HashMap<>();
	List<String> travelRoute = new ArrayList<>();

	public String[] solution(String[][] tickets) {

		// graph 그리기
		for (int i = 0; i < tickets.length; i++) {

			String start = tickets[i][0];
			String end = tickets[i][1];

			// 해당 출발 공항이 없는 경우 PriorityQueue 생성
			if (!graph.containsKey(start)) {
				graph.put(start, new PriorityQueue<>());
			}

			// 항공권 추가
			graph.get(start).add(end);
		}

		// ICN에서 오일러 경로 탐색 시작
		dfs("ICN");

		// Hierholzer 알고리즘은 경로가 역순으로 생성된다.
		Collections.reverse(travelRoute);

		return travelRoute.toArray(new String[0]);
	}

	public void dfs(String current) {

		// 현재 공항에서 사용할 수 있는 항공권이 존재하는 동안 탐색
		while (graph.containsKey(current)
				&& !graph.get(current).isEmpty()) {

			// 알파벳 순서가 가장 빠른 목적지 선택
			String next = graph.get(current).poll();

			// 다음 공항 탐색
			dfs(next);
		}

		// 더 이상 사용할 수 있는 항공권이 없으면
		// 현재 공항을 경로에 추가
		travelRoute.add(current);
	}
}

// 로직 (논리 + 코드 레벨)
/*
	Graph로 표현

	모든 항공권을 정확히 한 번씩 사용해야 하므로
	모든 간선을 한 번씩 사용하는 오일러 경로 문제이다.

	Hierholzer 알고리즘을 사용한다.

	1. ICN에서 DFS를 시작한다.
	2. 현재 공항에서 갈 수 있는 공항 중
	   알파벳 순서가 가장 빠른 공항을 선택한다.
	3. 사용한 항공권은 PriorityQueue에서 제거한다.
	4. 더 이상 사용할 수 있는 항공권이 없는 공항에 도착하면
	   해당 공항을 travelRoute에 추가한다.
	5. DFS가 종료되면서 경로가 역순으로 만들어진다.
	6. 마지막에 Collections.reverse()를 사용해 경로를 뒤집는다.

	PriorityQueue를 사용하기 때문에
	여러 경로가 가능한 경우 알파벳 순서가 앞서는 경로를 만들 수 있다.
*/

// 배운 것
/*
	1) 오일러 경로와 해밀턴 경로

	오일러 경로 (Euler Path):
	그래프의 모든 간선을 정확히 한 번씩 사용하는 경로이다.

	해밀턴 경로 (Hamiltonian Path):
	그래프의 모든 정점을 정확히 한 번씩 방문하는 경로이다.

	이 문제에서는 항공권 하나가 그래프의 간선 하나에 해당한다.
	모든 항공권을 사용해야 하므로 오일러 경로 문제이다.


	2) Hierholzer 알고리즘

	오일러 경로를 구하는 대표적인 알고리즘이다.

	일반 DFS처럼 방문하면서 경로를 확정하는 것이 아니라,
	더 이상 갈 수 없는 지점에서부터 역순으로 경로를 확정한다.

	예)

	ICN -> A
	ICN -> B
	B   -> ICN

	ICN에서 알파벳이 빠른 A를 먼저 선택하면

	ICN -> A

	A는 막다른 길이다.

	하지만 이 선택을 실패로 보고 백트래킹하는 것이 아니라,
	A를 최종 경로의 뒤쪽으로 확정한다.

	이후 남은 간선을 탐색하면 최종적으로

	ICN -> B -> ICN -> A

	가 만들어진다.


	3) 기존 백트래킹 풀이와 차이

	기존 풀이:

	간선 제거
	→ DFS
	→ 간선 복구
	→ 다른 경우 탐색
	→ 완성된 경로끼리 비교

	Hierholzer:

	간선 제거
	→ DFS
	→ 복구하지 않음
	→ DFS 종료 시 정점을 결과에 추가

	각 항공권을 실제 경로의 일부로 한 번만 소비하기 때문에
	백트래킹처럼 간선을 복구할 필요가 없다.


	4) 시간 복잡도

	기존 완전탐색 / 백트래킹:
	최악의 경우 경우의 수가 매우 크게 증가할 수 있다.

	Hierholzer + PriorityQueue:

	항공권 E개에 대해 PriorityQueue의 삽입 / 삭제가 O(log E)이므로

	전체 시간복잡도는 대략 O(E log E)이다.
*/

// input
/*

*/

// output
/*

*/

// 다른 사람 코드
/*

*/