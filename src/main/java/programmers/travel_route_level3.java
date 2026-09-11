package programmers;

import java.util.*;

public class travel_route_level3 {

	Map<String, List<String>> graph = new HashMap<>();
	List<String> travelRoute = new ArrayList<>();
	List<String> answerRoute;

	public String[] solution(String[][] tickets) {
		
		// graph 그리기
		for (int i = 0; i < tickets.length; i++) {
			
			// 해당 key 가지고 있는지 검사
			if (!graph.containsKey(tickets[i][0])) {
				// 해당 key가 없는 경우 추가
				List<String> arrayTemp = new ArrayList<>();
				arrayTemp.add(tickets[i][1]);
				graph.put(tickets[i][0], arrayTemp);

			} else {
				// 해당 key가 이미 있는 경우 value만 추가
				List<String> arrayTemp = graph.get(tickets[i][0]);
				arrayTemp.add(tickets[i][1]);
			}
		}

		travelRoute.add("ICN");

		dfs("ICN", tickets.length);

		return answerRoute.toArray(new String[0]);
	}

	public void dfs(String current, int ticketCount) {
		
		if (travelRoute.size() == ticketCount + 1) {

			// 처음 만들어진 여행 경로인 경우
			if (answerRoute == null) {
				answerRoute = new ArrayList<>(travelRoute);
			} else {
				
				// 기존 여행 경로와 새로 만들어진 여행 경로를 비교
				for (int i = 0; i < travelRoute.size(); i++) {
					// compareTo를 사용해서 알파벳 순서 비교
					int compare = travelRoute.get(i).compareTo(answerRoute.get(i));
					// 현재 만든 여행 경로가 더 앞서는 경우
					if (compare < 0) {
						// travelRoute는 계속 재사용하기에 복사본을 저장해야 한다 
						answerRoute = new ArrayList<>(travelRoute);
						break;
					}
					// 기존 여행 경로가 더 앞서는 경우
					else if (compare > 0) {
						break;
					}
				}
			}
			return;
		}

		// 경로 추가 로직 시작
		if (!graph.containsKey(current))
			return;

		List<String> currentArray = graph.get(current);

		// 이동 가능한 공항 순회
		for (int i = 0; i < currentArray.size(); i++) {

			// 사용한 항공권 제거 (간선 제거)
			String next = currentArray.remove(i);

			// 여행 경로에 다음 공항 추가
			travelRoute.add(next);

			// 다음 공항 탐색
			dfs(next, ticketCount);

			// 탐색이 끝났으면 간 복구
			travelRoute.remove(travelRoute.size() - 1);

			// 사용했던 항공권 다시 추가
			currentArray.add(i, next);
		}
	}
}

//로직 (논리 + 코드 레벨)
/*
	Graph로 표현 / 완전탐색 / 최단거리가 아닌 백트레킹 기반 최적 값이니 dfs
	
	dfs로 경로를 구하면서 모든 경로가 정해지면 travelRoute에 저장한다.
	추가로 경로가 구해지면 기존 travelRoute와 비교한다 (compareTo를 사용)
	알파벳 경로가 앞서는 경로로 업데이트 한다.
*/

//배운 것
/*	
	1) 오일러 경로와 해밀턴 경로
	오일러 경로:
	그래프에서 모든 간선을 방문하는 것을 오일러 경로라고 한다.
	해밀턴 경로:
	그래프에서 모든 정점을 방문하는 것을 해밀턴 경로라고 한다.
		
*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/
